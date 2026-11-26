package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.RUNTIME_CONFIG
import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.RuntimeConfigDto
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.w3c.xhr.XMLHttpRequest

/**
 * Reads the ports in use from the dashboard that serves this page and stores the API port,
 * so every request goes to the right port. If the file is not there (for example the
 * development server), the stored port (or the default one) is kept.
 */
object RuntimeConfigLoader {
    private const val TIMEOUT_MS = 3000
    private val json = Json { ignoreUnknownKeys = true }

    fun load(onDone: () -> Unit) {
        var finished = false
        fun finish() {
            if (finished) return
            finished = true
            onDone()
        }

        try {
            val xhr = XMLHttpRequest()
            xhr.open("GET", RUNTIME_CONFIG, true)
            xhr.timeout = TIMEOUT_MS
            xhr.onload = {
                if (xhr.status.toInt() in 200..299) apply(xhr.responseText)
                finish()
            }
            xhr.onerror = { finish() }
            xhr.ontimeout = { finish() }
            xhr.send()
        } catch (e: Throwable) {
            println("RuntimeConfigLoader:: ${e.message}")
            finish()
        }
    }

    private fun apply(body: String) {
        try {
            val config = json.decodeFromString<RuntimeConfigDto>(body)
            window.localStorage.setItem(API_PORT, config.apiPort.toString())
        } catch (e: Exception) {
            // A development server answers with index.html instead of the JSON
            println("RuntimeConfigLoader:: invalid runtime config: ${e.message}")
        }
    }
}
