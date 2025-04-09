package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto

class GetConnectionConfig(
    private val deviceRepository: DeviceRepository
) {
    suspend fun invoke(ipAddress: String): ServiceResult<ConnectionConfigDto> {
        return deviceRepository.getCurrentConfiguration(ipAddress)
    }
}