package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.DeviceModel
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toDto
import kotlinx.browser.window

class DeleteDevice(
    private val deviceRepository: DeviceRepository,
    private val preferences: WasmSharedPreferencesProvider
) {
    suspend fun invoke(model: DeviceModel): ServiceResult<ResponseStatusDto> {
        val ipaddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
        return deviceRepository.deleteDevice(ipaddress, model.toDto())
    }
}