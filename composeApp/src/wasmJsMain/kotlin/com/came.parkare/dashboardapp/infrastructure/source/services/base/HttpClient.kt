package com.came.parkare.dashboardapp.infrastructure.source.services.base

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.suspendCoroutine


class HttpClient() {
    val json = Json { ignoreUnknownKeys = true }

    // Generic GET method
    suspend inline fun <reified T> get(path: String): T = request<T, ResponseStatusDto>("GET", path, null)

    // Generic POST method
    suspend inline fun <reified T, reified R> post(path: String, requestBody: R): T =
        request("POST", path, requestBody)

    suspend inline fun <reified T, reified R> request(
        method: String,
        apiUrl: String,
        requestBody: R?
    ): T {
        return suspendCoroutine { continuation ->
            val xhr = XMLHttpRequest()

            xhr.open(method, apiUrl, true)
            xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8")
            xhr.setRequestHeader("Accept", "application/json")
            xhr.onload = {
                when (xhr.status) {
                    in 200..299 -> {
                        try {
                            val response: T = if (xhr.responseText.isEmpty()) {
                                Unit as T // For empty responses
                            } else {
                                json.decodeFromString(xhr.responseText)
                            }
                            continuation.resumeWith(Result.success(response))
                        } catch (e: Exception) {
                            continuation.resumeWith(Result.failure(
                                Exception("Failed to parse response: ${e.message}")
                            ))
                        }
                    }
                    in 401..402 -> {
                        window.location.href = "/login" // Example: Redirect on unauthorized
                        continuation.resumeWith(Result.failure(
                            Exception("Unauthorized - redirected to login")
                        ))
                    }
                    else -> {
                        continuation.resumeWith(Result.failure(
                            Exception("HTTP error ${xhr.status}: ${xhr.statusText}")
                        ))
                    }
                }
            }

            xhr.onerror = {
                continuation.resumeWith(Result.failure(
                    Exception("Network error when making $method request to $apiUrl")
                ))
            }

            val body = requestBody?.let { json.encodeToString(it) } ?: ""
            xhr.send(body)
        }
    }
}