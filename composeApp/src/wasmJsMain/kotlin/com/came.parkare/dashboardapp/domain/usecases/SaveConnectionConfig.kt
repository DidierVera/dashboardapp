package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import kotlinx.browser.window

class SaveConnectionConfig(
    private val preferences: WasmSharedPreferencesProvider,
    private val deviceRepository: DeviceRepository
) {
    suspend fun invoke(config: ConnectionConfigDto): ServiceResult<ResponseStatusDto> {
        val ipAddress = preferences.get(SELECTED_IP_ADDRESS, window.location.hostname)
        return deviceRepository.saveConnectionConfig(ipAddress, config)
    }
}