package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import kotlinx.browser.window

class GetDeviceStatus(
    private val deviceRepository: DeviceRepository,
    private val appLogger: AppLogger
) {
    suspend fun invoke(deviceIp: String = window.location.hostname): ServiceResult<Boolean>{
        return try {
            deviceRepository.getDeviceStatus(deviceIp)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }
}