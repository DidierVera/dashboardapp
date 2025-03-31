package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import android.util.Log
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetCurrentConfigRequest
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetDashboardListRequest
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveConnectionConfig
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveDeviceRequest
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveScreenRequest
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.readBodyAsUtf8
import dashboardapp.composeapp.generated.resources.Res
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

class AndroidApiServer(
    private val apiServerRepository: ApiServerRepository,
    port: Int = 2023
): NanoHTTPD(port) {
    private val TAG = "AndroidApiServer"
    private val serverScope = CoroutineScope(Job() + Dispatchers.Default)

    override fun serve(session: IHTTPSession): Response {
        Log.d(TAG, "Request: ${session.method} ${session.uri}")

        return when {
            session.isGetDashboardListRequest() -> handleGetDashboardList(session)
            session.isGetCurrentConfigRequest() -> handleGetCurrentConfiguration(session)
            session.isSaveDeviceRequest() -> handleSaveDevice(session)
            session.isSaveScreenRequest() -> handleSaveScreen(session)
            session.isSaveConnectionConfig() -> handleSaveTerminalConnection(session)
            else -> createNotFoundResponse()
        }
    }

    override fun stop() {
        serverScope.cancel()
        super.stop()
    }


    //region Request Handlers
    private fun handleSaveTerminalConnection(session: IHTTPSession): Response {
        return processPostRequest<ConnectionConfigDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> apiServerRepository.saveTerminalConnection(dto) == 0 }
        )
    }

    private fun handleGetDashboardList(session: IHTTPSession): Response {
        return processAsyncRequest<Unit, List<DeviceDto>>(
            operation = { apiServerRepository.getDashboardIpList() },
            successTransform = { result -> Json.encodeToString(result) }
        )
    }

    private fun handleGetCurrentConfiguration(session: IHTTPSession): Response {
        return processAsyncRequest<Unit, List<ScreenDto>>(
            operation = { apiServerRepository.getCurrentConfiguration() },
            successTransform = { result -> Json.encodeToString(result) }
        )
    }

    private fun handleSaveDevice(session: IHTTPSession): Response {
        return processPostRequest<DeviceDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString<DeviceDto>(body) },
            operation = { dto -> apiServerRepository.saveDashboardIp(dto) == 0 }
        )
    }

    private fun handleSaveScreen(session: IHTTPSession): Response {
        return processPostRequest<List<ScreenDto>>(
            session = session,
            parseBody = { body -> Json.decodeFromString<List<ScreenDto>>(body) },
            operation = { config -> apiServerRepository.saveScreensConfig(config) == 0 }
        )
    }
    //endregion

    //region Core Processing Methods
    private inline fun <reified T> processPostRequest(
        session: IHTTPSession,
        noinline parseBody: (String) -> T,
        noinline operation: suspend (T) -> Boolean
    ): Response {
        return processAsyncRequest(
            preProcess = {
                session.readBodyAsUtf8()
            },
            parse = parseBody,
            operation = operation,
            successTransform = { result -> """{"status": $result}""" }

        )
    }

    private inline fun <T, R> processAsyncRequest(
        noinline preProcess: (suspend () -> String)? = null,
        noinline parse: ((String) -> T)? = null,
        noinline operation: suspend (T) -> R,
        noinline successTransform: (R) -> String
    ): Response {
        val deferred = CompletableDeferred<Response>()

        serverScope.launch {
            try {
                val input = preProcess?.let { it() }
                val parsedInput = input?.let { parse?.invoke(it) } ?: Unit as T
                val result = operation(parsedInput)

                deferred.complete(
                    createSuccessResponse(successTransform(result)))

            } catch (e: Exception) {
                deferred.complete(createErrorResponse(e))
            }
        }

        return runBlocking { deferred.await() }
    }
    //endregion

    //region Response Builders
    private fun createSuccessResponse(body: String): Response {
        return newFixedLengthResponse(
            Response.Status.OK,
            "application/json; charset=utf-8",  // Explicit charset
            body
        ).apply {
            addHeader("Content-Type", "application/json; charset=utf-8")
        }
    }

    private fun createErrorResponse(exception: Exception): Response {
        exception.printStackTrace()
        return newFixedLengthResponse(
            if (exception is IllegalArgumentException) Response.Status.BAD_REQUEST
            else Response.Status.INTERNAL_ERROR,
            "application/json",
            """{"error": "${exception.message.orEmpty()}"}"""
        )
    }

    private fun createNotFoundResponse(): Response {
        return newFixedLengthResponse(
            Response.Status.NOT_FOUND,
            "text/plain",
            "404 Not Found"
        )
    }
    //endregion
}