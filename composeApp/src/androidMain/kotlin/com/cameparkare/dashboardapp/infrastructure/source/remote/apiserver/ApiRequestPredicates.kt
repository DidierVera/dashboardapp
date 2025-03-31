package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import fi.iki.elonen.NanoHTTPD
import kotlinx.serialization.json.Json

object ApiRequestPredicates {
    fun NanoHTTPD.IHTTPSession.isGetDashboardListRequest(): Boolean {
        return uri == "/api/device/dashboardList" && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isGetCurrentConfigRequest(): Boolean {
        return uri == "/api/screen/getcurrentconfiguration" && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isSaveDeviceRequest(): Boolean {
        return uri == "/api/device/save" && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isSaveScreenRequest(): Boolean {
        return uri == "/api/screen/save" && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.readBodyAsUtf8(): String {
        val files = mutableMapOf<String, String>()
        this.parseBody(files)
        return files.values.first().let { body ->
            if (body.startsWith('\uFEFF')) {
                body.substring(1) // Remove BOM if present
            } else {
                String(body.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)
            }
        }
    }

    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        explicitNulls = false
        // Important for special characters:
        allowStructuredMapKeys = true
    }
}