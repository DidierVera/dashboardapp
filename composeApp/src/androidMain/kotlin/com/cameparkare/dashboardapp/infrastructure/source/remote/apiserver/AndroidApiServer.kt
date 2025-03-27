package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import android.util.Log
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class AndroidApiServer(
    private val apiServerRepository: ApiServerRepository,
    port: Int = 2023
): NanoHTTPD(port) {
    private val TAG = "AndroidApiServer"
    private val serverScope = CoroutineScope(Job() + Dispatchers.Default)

    override fun serve(session: IHTTPSession): Response {

        val method = session.method
        val uri = session.uri
        val params = session.parameters

        val response = CompletableDeferred<Response>()
        Log.d(TAG, "Request: $method $uri")

        return when {
            // GET Dashboard ip list
            uri == "/api/dashboardList" && method == Method.GET -> {
                serverScope.launch {
                    try {
                        val result = apiServerRepository.getDashboardIpList()
                        response.complete(
                            newFixedLengthResponse(
                                Response.Status.OK,
                                "application/json",
                                Json.encodeToString(result)
                            )
                        )
                    } catch (e: Exception) {
                        response.complete(
                            newFixedLengthResponse(
                                Response.Status.INTERNAL_ERROR,
                                "application/json",
                                """{"error": "${e.message}"}"""
                            )
                        )
                    }
                }

                runBlocking { response.await() }
            }

            uri == "/api/getcurrentconfiguration" && method == Method.GET  ->{
                serverScope.launch {
                    try {
                        val result = apiServerRepository.getCurrentConfiguration()
                        response.complete(
                            newFixedLengthResponse(
                                Response.Status.OK,
                                "application/json",
                                Json.encodeToString(result)
                            )
                        )
                    } catch (e: Exception) {
                        response.complete(
                            newFixedLengthResponse(
                                Response.Status.INTERNAL_ERROR,
                                "application/json",
                                """{"error": "${e.message}"}"""
                            )
                        )
                    }
                }

                runBlocking { response.await() }
            }

            // POST /api/data
            uri == "/api/data" && method == Method.POST -> {
                try {
                    val files = mutableMapOf<String, String>()
                    session.parseBody(files)

                    // Return the parsed body
                    val response = """{"message": "Hello, "status": "success", ${files.values}!"}"""
                    newFixedLengthResponse(Response.Status.OK, "application/json",
                        """{
                            "status": "success",
                            "received": ${files.values}
                            
                        }""".trimIndent())

                } catch (e: Exception) {
                    e.printStackTrace()
                    newFixedLengthResponse(Response.Status.BAD_REQUEST, "application/json",
                        """{"error": "Invalid request: ${e.message}"}""")
                }
            }

            else -> newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404 Not Found")
        }
    }

    override fun stop() {
        serverScope.cancel()
        super.stop()
    }
}