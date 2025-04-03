package com.cameparkare.dashboardapp.infrastructure.source.services

import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.suspendCoroutine


class HttpClient(basePath: String = "") {
    val json = Json { ignoreUnknownKeys = true }
    val baseUrl = "${window.location.protocol}//${window.location.hostname}$basePath"

    // Generic GET method
    suspend inline fun <reified T> get1(path: String): T = request<T, Int>("GET", path, null)

    // Generic POST method
    suspend inline fun <reified T, reified R> post(path: String, requestBody: R): T =
        request("POST", path, requestBody)

    suspend inline fun <reified T, reified R> request(
        method: String,
        path: String,
        requestBody: R?
    ): T {
        return suspendCoroutine { continuation ->
            val url = "$baseUrl$path"
            val xhr = XMLHttpRequest()

            xhr.open(method, url, true)
            xhr.setRequestHeader("Content-Type", "application/json")
            xhr.setRequestHeader("Accept", "application/json")

            xhr.onload = {
                when (xhr.status) {
                    in 200..299 -> {
                        try {
                            val response: T = if (xhr.responseText.isNullOrEmpty()) {
                                Unit as T // For empty responses
                            } else {
                                json.decodeFromString(xhr.responseText)
                            }
                            continuation.resumeWith(Result.success(response))
                        } catch (e: Exception) {
                            continuation.resumeWith(Result.failure(
                                Error("Failed to parse response: ${e.message}")
                            ))
                        }
                    }
                    in 401..402 -> {
                        window.location.href = "/login" // Example: Redirect on unauthorized
                        continuation.resumeWith(Result.failure(
                            Error("Unauthorized - redirected to login")
                        ))
                    }
                    else -> {
                        continuation.resumeWith(Result.failure(
                            Error("HTTP error ${xhr.status}: ${xhr.statusText}")
                        ))
                    }
                }
            }

            xhr.onerror = {
                continuation.resumeWith(Result.failure(
                    Error("Network error when making $method request to $url")
                ))
            }

            val body = requestBody?.let { json.encodeToString(it) } ?: ""
            xhr.send(body)
        }
    }
}