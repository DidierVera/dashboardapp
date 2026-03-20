package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts.FontUploadResponseDto
import kotlinx.browser.window

class SaveFonts(
    private val preferences: WasmSharedPreferencesProvider,
    private val deviceRepository: DeviceRepository
) {

    suspend fun invoke(data:List<ResourceFileDto>):  ServiceResult<ResponseStatusDto> {
        val ipAddress = preferences.get(SELECTED_IP_ADDRESS, window.location.hostname)
        for (font in data){
            when(val result = deviceRepository.uploadFont(ipAddress, font)){
                is ServiceResult.Error -> {
                    return ServiceResult.Error(result.error)
                }
                is ServiceResult.Success -> continue
            }
        }
        return ServiceResult.Success(ResponseStatusDto(true))
    }
}