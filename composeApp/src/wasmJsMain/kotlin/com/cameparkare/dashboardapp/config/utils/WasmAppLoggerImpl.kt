package com.cameparkare.dashboardapp.config.utils

import com.cameparkare.dashboardapp.infrastructure.source.services.base.HttpClient

class WasmAppLoggerImpl(
    private val httpClient: HttpClient
): AppLogger {
    override fun trackError(e: Exception) {
        e.printStackTrace()
    }

    override fun trackLog(tag: String, message: String?) {
    }
}