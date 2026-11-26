package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DisplaySizeDto
import kotlinx.browser.window

/**
 * Size of the screen of the selected monitor. Monitors prior to 2.3.0 do not have the
 * endpoint, so the size of the DG03-A1W55 in portrait is used for them.
 */
class GetDisplaySize(
    private val preferences: WasmSharedPreferencesProvider,
    private val deviceRepository: DeviceRepository,
    private val appLogger: AppLogger
) {
    suspend fun invoke(): DisplaySizeDto {
        return try {
            val ipAddress = preferences.get(SELECTED_IP_ADDRESS, window.location.hostname)
            when (val result = deviceRepository.getDisplaySize(ipAddress)) {
                is ServiceResult.Success ->
                    result.data?.takeIf { it.widthDp > 0 && it.heightDp > 0 } ?: DisplaySizeDto.DEFAULT
                is ServiceResult.Error -> DisplaySizeDto.DEFAULT
            }
        } catch (e: Exception) {
            appLogger.trackError(e)
            DisplaySizeDto.DEFAULT
        }
    }
}
