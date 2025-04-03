package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardDevicesRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.domain.repositories.remote.ApiServerRepository
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.toDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.toModel

class ApiServerRepositoryImpl(
    private val dashboardElementRepository: DashboardElementRepository,
    private val serverConnection: IServerConnection,
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