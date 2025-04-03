package com.cameparkare.dashboardapp.infrastructure.repositories.device

import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.source.services.base.HttpClient

class DeviceRepositoryImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger
): DeviceRepository {
    override suspend fun getCurrentConfiguration(ipAddress: String): ServiceResult<ConnectionConfigDto> {
        return try {
            val currentConfig = httpClient.get<ConnectionConfigDto>("http://$ipAddress:2023/api/connection/get")
            ServiceResult.Success(currentConfig)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun getDevicesList(ipAddress: String): ServiceResult<List<DeviceDto>> {
        return try {
            val devices = httpClient.get<List<DeviceDto>>("http://$ipAddress:2023/api/device/get")
            ServiceResult.Success(devices)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun saveConnectionConfig(
        ipAddress: String,
        config: ConnectionConfigDto
    ): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, ConnectionConfigDto>("http://$ipAddress:2023/api/connection/save", config)
            ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun saveDeviceInfo(ipAddress: String, device: DeviceDto): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, DeviceDto>("http://$ipAddress:2023/api/device/save", device)
            ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }
}