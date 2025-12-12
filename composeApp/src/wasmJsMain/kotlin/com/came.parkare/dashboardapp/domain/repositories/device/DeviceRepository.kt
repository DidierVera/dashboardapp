package com.came.parkare.dashboardapp.domain.repositories.device

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.models.DeviceModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto

interface DeviceRepository {
    suspend fun getCurrentConfiguration(ipAddress: String): ServiceResult<ConnectionConfigDto>
    suspend fun getDevicesList(ipAddress: String): ServiceResult<List<DeviceDto>>
    suspend fun saveConnectionConfig(ipAddress: String, config: ConnectionConfigDto): ServiceResult<ResponseStatusDto>
    suspend fun saveDeviceInfo(ipAddress: String, device: DeviceDto): ServiceResult<ResponseStatusDto>
    suspend fun deleteDevice(ipAddress: String, deviceModel: DeviceDto): ServiceResult<ResponseStatusDto>
    suspend fun getDeviceStatus(ipAddress: String): ServiceResult<Boolean>
    suspend fun getAppVersion(ipAddress: String): ServiceResult<String>
}