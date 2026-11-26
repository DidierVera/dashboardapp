package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.config.dataclasses.AppVersion
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DisplaySizeDto

interface DeviceUtils {
    fun getAppVersion(): AppVersion?
    fun getDeviceSerialNumber(): String?
    /** Size of the screen in dp in its current orientation, or null if it is not known */
    fun getDisplaySize(): DisplaySizeDto? = null
}
