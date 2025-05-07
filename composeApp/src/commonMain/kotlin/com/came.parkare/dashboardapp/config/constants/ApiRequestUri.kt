package com.came.parkare.dashboardapp.config.constants

object ApiRequestUri {
    const val SSL_PROTOCOL = "http://"
    const val GET_SCREENS_CONFIG = "/api/screen/get"
    const val SAVE_SCREEN_CONFIG = "/api/screen/save"
    const val GET_SCREEN_CONFIG_TYPE = "/api/screen/config/type/get"
    const val SAVE_SCREEN_CONFIG_TYPE = "/api/screen/config/type/save"
    const val SAVE_CONFIG_TEMPLATE = "/api/screen/config/template/save"
    const val GET_CONFIG_TEMPLATE = "/api/screen/config/template/get"
    const val UPDATE_CONFIG_TEMPLATE = "/api/screen/config/template/update"
    const val DELETE_CONFIG_TEMPLATE = "/api/screen/config/template/delete"
    const val GET_CONNECTION_CONFIG = "/api/connection/get"
    const val SAVE_CONNECTION_CONFIG = "/api/connection/save"
    const val GET_DEVICE_LIST = "/api/device/get"
    const val SAVE_DEVICE = "/api/device/save"
    const val DELETE_DEVICE = "/api/device/delete"
    const val TRACK_ERROR = "/api/configurator/error"
    const val TRACK_LOG = "/api/configurator/log"
    const val CHECK_STATUS = "/api/device/status"
}