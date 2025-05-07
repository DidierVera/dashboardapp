package com.came.parkare.dashboardapp.infrastructure.repositories.device

import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.DELETE_DEVICE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_CONNECTION_CONFIG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_DEVICE_LIST
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_CONNECTION_CONFIG
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_DEVICE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SSL_PROTOCOL
import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.came.parkare.dashboardapp.infrastructure.source.services.base.HttpClient

class DeviceRepositoryImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger,
    private val preferences: WasmSharedPreferencesProvider
): DeviceRepository {
    private val apiPort = preferences.get(API_PORT, 2023)
    override suspend fun getCurrentConfiguration(ipAddress: String): ServiceResult<ConnectionConfigDto> {
        return try {
            val currentConfig = httpClient.get<ConnectionConfigDto>("$SSL_PROTOCOL$ipAddress:$apiPort$GET_CONNECTION_CONFIG")
            ServiceResult.Success(currentConfig)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun getDevicesList(ipAddress: String): ServiceResult<List<DeviceDto>> {
        return try {
            val devices = httpClient.get<List<DeviceDto>>("$SSL_PROTOCOL$ipAddress:$apiPort$GET_DEVICE_LIST")
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
            val result = httpClient.post<ResponseStatusDto, ConnectionConfigDto>("$SSL_PROTOCOL$ipAddress:$apiPort$SAVE_CONNECTION_CONFIG", config)
            ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun saveDeviceInfo(ipAddress: String, device: DeviceDto): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, DeviceDto>("$SSL_PROTOCOL$ipAddress:$apiPort$SAVE_DEVICE", device)
            ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun deleteDevice(
        ipAddress: String,
        deviceModel: DeviceDto
    ): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, DeviceDto>("$SSL_PROTOCOL$ipAddress:$apiPort$DELETE_DEVICE", deviceModel)
            ServiceResult.Success(result)

        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }
}