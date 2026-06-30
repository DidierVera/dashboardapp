package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto
import kotlinx.browser.window

class GetImages(
    private val preferences: WasmSharedPreferencesProvider,
    private val deviceRepository: DeviceRepository
) {
    suspend fun invoke(): ServiceResult<List<ResourceFileDto>?> {
        val ipAddress = preferences.get(SELECTED_IP_ADDRESS, window.location.hostname)
        return when(val result = deviceRepository.getImages(ipAddress)){
            is ServiceResult.Error -> ServiceResult.Error(result.error)
            is ServiceResult.Success -> result
        }
    }
}