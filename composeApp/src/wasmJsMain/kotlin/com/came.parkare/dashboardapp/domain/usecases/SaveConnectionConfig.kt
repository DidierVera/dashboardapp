package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto

class SaveConnectionConfig(
    private val deviceRepository: DeviceRepository
) {
    suspend fun invoke(ipAddress: String, config: ConnectionConfigDto): ServiceResult<ResponseStatusDto> {
        return deviceRepository.saveConnectionConfig(ipAddress, config)
    }
}