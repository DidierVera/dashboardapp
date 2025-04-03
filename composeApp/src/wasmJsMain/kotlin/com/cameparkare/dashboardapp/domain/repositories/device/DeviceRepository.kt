package com.cameparkare.dashboardapp.domain.repositories.device

import com.cameparkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto

interface DeviceRepository {
    suspend fun getCurrentConfiguration(ipAddress: String): ServiceResult<ConnectionConfigDto>
    suspend fun getDevicesList(ipAddress: String): ServiceResult<List<DeviceDto>>
    suspend fun saveConnectionConfig(ipAddress: String, config: ConnectionConfigDto): ServiceResult<ResponseStatusDto>
    suspend fun saveDeviceInfo(ipAddress: String, device: DeviceDto): ServiceResult<ResponseStatusDto>
}