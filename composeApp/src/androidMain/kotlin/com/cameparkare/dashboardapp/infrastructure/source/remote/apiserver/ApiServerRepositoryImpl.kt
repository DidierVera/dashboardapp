package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_API
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_IP
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.cameparkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.cameparkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.cameparkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.models.ImagesModel
import com.cameparkare.dashboardapp.domain.models.ConnectionConfigModel
import com.cameparkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardDevicesRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.toDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.toModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.toDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.toModel

class ApiServerRepositoryImpl(
    private val dashboardElementRepository: DashboardElementRepository,
    private val serverConnection: IServerConnection,
    private val preferences: SharedPreferencesProvider,
    private val configFileRepository: ConfigFileRepository,
    private val dashboardDevicesRepository: DashboardDevicesRepository
): ApiServerRepository {
    override suspend fun saveDashboardIp(device: DeviceDto): Int {
        dashboardDevicesRepository.saveDevice(device.toModel())
        return 0
    }

    override suspend fun saveScreensConfig(screensConfig: List<ScreenDto>): Int {
        dashboardElementRepository.saveScreens(screensConfig.map { it.toModel() })
        configFileRepository.writeScreensConfig(screensConfig.map { it.toModel() })
        serverConnection.setRestartApp(true)
        return 0
    }

    override suspend fun getDashboardIpList(): List<DeviceDto> {
        val result = dashboardDevicesRepository.getDevicesList()
        return result.map { it.toDto() }
    }

    override suspend fun updateDashboardDeviceInfo(id: Long, newData: DeviceDto): Int {
        dashboardDevicesRepository.updateDeviceInfo(id, newData.toModel())
        return 0
    }

    override suspend fun deleteDashboardDevice(id: Long): Int {
        dashboardDevicesRepository.deleteDevice(id)
        return 0
    }

    override suspend fun saveTerminalConnection(data: ConnectionConfigDto): Int {
        configFileRepository.writeConnectionConfig(data.toModel())
        serverConnection.setRestartApp(true)
        return 0
    }

    override suspend fun getCurrentConfiguration(): List<ScreenDto> {
        val result = dashboardElementRepository.getAllScreens()
        return result.map { it.toDto() }
    }
}