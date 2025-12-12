package com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver

import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.CHECK_STATUS
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.DELETE_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.DELETE_DEVICE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_CONNECTION_CONFIG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_DEFAULT_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_DEVICE_LIST
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_SCREENS_CONFIG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_SCREEN_CONFIG_TYPE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_VERSION
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_CONNECTION_CONFIG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_DEVICE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_SCREEN_CONFIG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_SCREEN_CONFIG_TYPE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.TRACK_ERROR
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.TRACK_LOG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.UPDATE_CONFIG_TEMPLATE
import fi.iki.elonen.NanoHTTPD

object ApiRequestPredicates {
    fun NanoHTTPD.IHTTPSession.isGetDashboardListRequest(): Boolean {
        return uri == GET_DEVICE_LIST && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isGetCurrentConfigRequest(): Boolean {
        return uri == GET_SCREENS_CONFIG && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isGetCurrentConnectionConfig(): Boolean {
        return uri == GET_CONNECTION_CONFIG && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isSaveDeviceRequest(): Boolean {
        return uri == SAVE_DEVICE && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isDeleteDevice(): Boolean {
        return uri == DELETE_DEVICE && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isSaveScreenRequest(): Boolean {
        return uri == SAVE_SCREEN_CONFIG && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isSaveConnectionConfig(): Boolean {
        return uri == SAVE_CONNECTION_CONFIG && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isSetConfigType(): Boolean {
        return uri == SAVE_SCREEN_CONFIG_TYPE && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isGetConfigType(): Boolean {
        return uri == GET_SCREEN_CONFIG_TYPE && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isDeleteConfigTemplate(): Boolean {
        return uri == DELETE_CONFIG_TEMPLATE && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isGetConfigTemplate(): Boolean {
        return uri == GET_CONFIG_TEMPLATE && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isGetDefaultConfigTemplate(): Boolean {
        return uri == GET_DEFAULT_CONFIG_TEMPLATE && method == NanoHTTPD.Method.GET
    }
    fun NanoHTTPD.IHTTPSession.isCheckStatus(): Boolean {
        return uri == CHECK_STATUS && method == NanoHTTPD.Method.GET
    }

    fun NanoHTTPD.IHTTPSession.isSaveConfigTemplate(): Boolean {
        return uri == SAVE_CONFIG_TEMPLATE && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isSaveConfiguratorLog(): Boolean {
        return uri == TRACK_LOG && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isSaveConfiguratorError(): Boolean {
        return uri == TRACK_ERROR && method == NanoHTTPD.Method.POST
    }

    fun NanoHTTPD.IHTTPSession.isUpdateConfigTemplate(): Boolean {
        return uri == UPDATE_CONFIG_TEMPLATE && method == NanoHTTPD.Method.POST
    }
    fun NanoHTTPD.IHTTPSession.isGetVersion(): Boolean {
        return uri == GET_VERSION && method == NanoHTTPD.Method.GET
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