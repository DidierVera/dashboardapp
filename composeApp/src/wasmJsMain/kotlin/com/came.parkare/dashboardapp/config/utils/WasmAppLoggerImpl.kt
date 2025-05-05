package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.infrastructure.source.services.base.HttpClient

class WasmAppLoggerImpl(
    private val httpClient: HttpClient
): AppLogger {
    override fun trackError(e: Exception) {
        e.printStackTrace()
    }

    override fun trackLog(tag: String, message: String?) {
    }

    override fun trackEvent(eventName: String, params: Map<String, Any>) {

    }
}