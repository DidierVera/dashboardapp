package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import kotlinx.browser.window

class GetDeviceStatus(
    private val deviceRepository: DeviceRepository
) {
    suspend fun invoke(deviceIp: String = window.location.hostname){

    }
}