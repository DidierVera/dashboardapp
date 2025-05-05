package com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver

import android.util.Log
import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.toDto
import com.came.parkare.dashboardapp.domain.repositories.local.ConfigTemplateRepository
import com.came.parkare.dashboardapp.domain.repositories.remote.ApiServerRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackErrorDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackLogDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ConfigTemplateDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isDeleteConfigTemplate
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isDeleteDevice
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetConfigTemplate
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetConfigType
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetCurrentConfigRequest
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetCurrentConnectionConfig
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isGetDashboardListRequest
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveConfigTemplate
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveConfiguratorError
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveConfiguratorLog
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveConnectionConfig
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveDeviceRequest
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSaveScreenRequest
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isSetConfigType
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.isUpdateConfigTemplate
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiRequestPredicates.readBodyAsUtf8
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class AndroidApiServer(
    private val preferences: SharedPreferencesProvider,
    private val apiServerRepository: ApiServerRepository,
    private val configTemplateRepository: ConfigTemplateRepository,
    private val appLogger: AppLogger,
    port: Int = preferences.get(API_PORT, 2023)
): NanoHTTPD(port) {
    private val TAG = "AndroidApiServer"

    override fun serve(session: IHTTPSession): Response {
        Log.d(TAG, "Request: ${session.method} ${session.uri}")

        // Handle OPTIONS (preflight) requests
        if (session.method == Method.OPTIONS) {
            return newFixedLengthResponse(Response.Status.OK, "text/plain", "").apply {
                addCorsHeaders(this)
            }
        }
        return when {
            session.isGetDashboardListRequest() -> handleGetDashboardList()
            session.isGetCurrentConfigRequest() -> handleGetCurrentConfiguration()
            session.isGetCurrentConnectionConfig() -> handleGetCurrentConnectionConfig()
            session.isGetConfigType() -> handleGetConfigType()
            session.isSaveDeviceRequest() -> handleSaveDevice(session)
            session.isDeleteDevice() -> handleDeleteDevice(session)
            session.isSaveScreenRequest() -> handleSaveScreen(session)
            session.isSaveConnectionConfig() -> handleSaveTerminalConnection(session)
            session.isSetConfigType() -> handleSetConfigType(session)
            session.isDeleteConfigTemplate() -> handleDeleteConfigTemplate(session)
            session.isGetConfigTemplate() -> handleGetConfigTemplate()
            session.isSaveConfigTemplate() -> handleSaveConfigTemplate(session)
            session.isUpdateConfigTemplate() -> handleUpdateConfigTemplate(session)
            session.isSaveConfiguratorLog() -> handleTrackLog(session)
            session.isSaveConfiguratorError() -> handleTrackError(session)
            else -> createNotFoundResponse()
        }
    }

    //region Request Handlers
    private fun handleTrackError(session: IHTTPSession): Response {
        return processPostRequest<TrackErrorDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> apiServerRepository.saveConfiguratorException(dto) == 0 }
        )
    }

    private fun handleTrackLog(session: IHTTPSession): Response {
        return processPostRequest<TrackLogDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> apiServerRepository.saveConfiguratorLog(dto) == 0 }
        )
    }

    private fun handleDeleteConfigTemplate(session: IHTTPSession): Response {
        return processPostRequest<ConfigTemplateDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> configTemplateRepository.deleteTemplate(dto.toModel()) }
        )
    }

    private fun handleUpdateConfigTemplate(session: IHTTPSession): Response {
        return processPostRequest<ConfigTemplateDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> configTemplateRepository.updateTemplate(dto.toModel()) }
        )
    }

    private fun handleSaveConfigTemplate(session: IHTTPSession): Response {
        return processPostRequest<ConfigTemplateDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> configTemplateRepository.saveTemplate(dto.toModel()) }
        )
    }

    private fun handleSetConfigType(session: IHTTPSession): Response {
        return processPostRequest<Int>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> apiServerRepository.saveScreenConfigType(dto) }
        )
    }

    private fun handleGetConfigTemplate(): Response {
        return processAsyncRequest<Unit, List<ConfigTemplateDto>>(
            operation = { configTemplateRepository.getAll().map { it.toDto() } },
            successTransform = { result -> Json.encodeToString(result) }
        )
    }
    private fun handleGetConfigType(): Response {
        return processAsyncRequest<Unit, Long>(
            operation = { apiServerRepository.getScreenConfigType() },
            successTransform = { result -> Json.encodeToString(result) }
        )
    }

    private fun handleSaveTerminalConnection(session: IHTTPSession): Response {
        return processPostRequest<ConnectionConfigDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString(body) },
            operation = { dto -> apiServerRepository.saveTerminalConnection(dto) == 0 }
        )
    }

    private fun handleGetDashboardList(): Response {
        return processAsyncRequest<Unit, List<DeviceDto>>(
            operation = { apiServerRepository.getDashboardIpList() },
            successTransform = { result -> Json.encodeToString(result) }
        )
    }

    private fun handleGetCurrentConnectionConfig(): Response {
        return processAsyncRequest<Unit, ConnectionConfigDto>(
            operation = { apiServerRepository.getCurrentConnectionConfig() },
            successTransform = { result -> Json.encodeToString(result) }
        )
    }

    private fun handleGetCurrentConfiguration(): Response {
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

    private fun handleDeleteDevice(session: IHTTPSession): Response {
        return processPostRequest<DeviceDto>(
            session = session,
            parseBody = { body -> Json.decodeFromString<DeviceDto>(body) },
            operation = { dto -> apiServerRepository.deleteDashboardDevice(dto.id) == 0 }
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
        return runBlocking {
            try {
                val input = preProcess?.let { it() }
                val parsedInput = input?.let { parse?.invoke(it) } ?: Unit as T
                val result = operation(parsedInput)
                createSuccessResponse(successTransform(result))
            }catch (e: Exception){
                appLogger.trackError(e)
                createErrorResponse(e)
            }
        }
    }
    //endregion

    //region Response Builders
    private fun createSuccessResponse(body: String): Response {
        return newFixedLengthResponse(
            Response.Status.OK,
            "application/json; charset=utf-8",  // Explicit charset
            body
        ).apply {
            addCorsHeaders(this)
        }
    }

    private fun createErrorResponse(exception: Exception): Response {
        exception.printStackTrace()
        return newFixedLengthResponse(
            if (exception is IllegalArgumentException) Response.Status.BAD_REQUEST
            else Response.Status.INTERNAL_ERROR,
            "application/json",
            """{"error": "${exception.message.orEmpty()}"}"""
        ).apply {
            addCorsHeaders(this)
        }
    }

    private fun createNotFoundResponse(): Response {
        return newFixedLengthResponse(
            Response.Status.NOT_FOUND,
            "text/plain",
            "404 Not Found"
        ).apply {
            addCorsHeaders(this)
        }
    }

    private fun addCorsHeaders(response: Response) {
        response.addHeader("Access-Control-Allow-Origin", "*")
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
        response.addHeader("Access-Control-Allow-Credentials", "true")
        response.addHeader("Access-Control-Max-Age", "3600")
    }
    //endregion
}