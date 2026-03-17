package com.came.parkare.dashboardapp.domain.repositories.device

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts.FontDeleteResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts.FontInfoDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts.FontUploadResponseDto

interface DeviceRepository {
    suspend fun getCurrentConfiguration(ipAddress: String): ServiceResult<ConnectionConfigDto>
    suspend fun getDevicesList(ipAddress: String): ServiceResult<List<DeviceDto>>
    suspend fun saveConnectionConfig(ipAddress: String, config: ConnectionConfigDto): ServiceResult<ResponseStatusDto>
    suspend fun saveDeviceInfo(ipAddress: String, device: DeviceDto): ServiceResult<ResponseStatusDto>
    suspend fun deleteDevice(ipAddress: String, deviceModel: DeviceDto): ServiceResult<ResponseStatusDto>
    suspend fun getDeviceStatus(ipAddress: String): ServiceResult<Boolean>
    suspend fun getAppVersion(ipAddress: String): ServiceResult<String>
    suspend fun getImages(ipAddress: String): ServiceResult<List<ResourceFileDto>?>
    suspend fun saveImages(ipAddress: String, data: List<ResourceFileDto>): ServiceResult<ResponseStatusDto>
    suspend fun uploadFont(ipAddress: String, fontFile: ByteArray, fileName: String, overwrite: Boolean = false): ServiceResult<FontUploadResponseDto>
    suspend fun listFonts(ipAddress: String): ServiceResult<List<FontInfoDto>>
    suspend fun deleteFont(ipAddress: String, fileName: String): ServiceResult<FontDeleteResponseDto>
    suspend fun downloadFont(ipAddress: String, fileName: String): ServiceResult<ByteArray>
}