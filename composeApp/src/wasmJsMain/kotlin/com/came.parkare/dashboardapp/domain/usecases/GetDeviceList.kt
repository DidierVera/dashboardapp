package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.DeviceModel
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import kotlinx.browser.window

class GetDeviceList(
    private val deviceRepository: DeviceRepository,
    private val preferences: WasmSharedPreferencesProvider
) {

    suspend fun invoke(): ServiceResult<List<DeviceModel>> {
        val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
        val result = deviceRepository.getDevicesList(ipAddress)
        return when (result){
            is ServiceResult.Error -> ServiceResult.Error(result.error)
            is ServiceResult.Success -> ServiceResult.Success(result.data.orEmpty().map { it.toModel() })
        }
    }
}