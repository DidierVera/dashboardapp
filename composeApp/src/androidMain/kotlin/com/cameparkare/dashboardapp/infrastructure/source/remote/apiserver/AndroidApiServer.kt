package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import android.util.Log
import fi.iki.elonen.NanoHTTPD

class AndroidApiServer: NanoHTTPD(2023) { // Puerto 2023
    private val TAG = "AndroidApiServer"
    override fun serve(session: IHTTPSession): Response {
        val method = session.method
        val uri = session.uri
        val params = session.parms

        Log.d(TAG, "Request: $method $uri")

        return when {
            // GET /api/hello?name=John
            uri == "/api/hello" && method == Method.GET -> {
                val name = params["name"] ?: "World"
                val response = """{"message": "Hello, $name!"}"""
                newFixedLengthResponse(Response.Status.OK, "application/json", response)
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
}
fun String.escapeJson(): String {
    return this.replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t")
}