package com.came.parkare.dashboardapp.config.utils

interface AppLogger {
    fun trackError(e: Exception)
    fun trackLog(tag: String, message: String? = null)
    fun trackEvent(eventName: String, params: Map<String, Any>)
}