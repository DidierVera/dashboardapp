package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.config.dataclasses.AppVersion

interface DeviceUtils {
    fun getAppVersion(): AppVersion?
    fun getDeviceSerialNumber(): String?
}