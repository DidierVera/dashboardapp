package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import fi.iki.elonen.NanoHTTPD

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

    fun NanoHTTPD.IHTTPSession.isSaveConnectionConfig(): Boolean {
        return uri == "/api/connection/config" && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.readBodyAsUtf8(): String {
        val files = mutableMapOf<String, String>()
        this.parseBody(files)
        return files.values.first().let { body ->
            if (body.startsWith('\uFEFF')) {
                body.substring(1) // Remove BOM if present
            } else {
                String(body.toByteArray(Charsets.UTF_8), Charsets.UTF_8)
            }
        }
    }
}