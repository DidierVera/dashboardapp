package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import kotlinx.browser.window

class GetConnectionConfig(
    private val preferences: WasmSharedPreferencesProvider,
    private val deviceRepository: DeviceRepository
) {
    suspend fun invoke(): ServiceResult<ConnectionConfigDto> {
        val ipAddress = preferences.get(SELECTED_IP_ADDRESS, window.location.hostname)
        // The API port is not taken from here: this is the value of the file, which may be
        // invalid or busy; the port in use comes from /runtime-config.json (see main.kt)
        return deviceRepository.getCurrentConfiguration(ipAddress)
    }
}