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
import com.cameparkare.dashboardapp.domain.models.TerminalConnectionModel
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardDevicesRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.toDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.toModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.toDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.toModel

class ApiServerRepositoryImpl(
    private val dashboardElementRepository: DashboardElementRepository,
    private val serverConnection: IServerConnection,
    private val preferences: SharedPreferencesProvider,
    private val dashboardDevicesRepository: DashboardDevicesRepository
): ApiServerRepository {
    override suspend fun saveDashboardIp(device: DeviceDto): Int {
        dashboardDevicesRepository.saveDevice(device.toModel())
        return 0
    }

    override suspend fun saveScreensConfig(screensConfig: List<ScreenDto>): Int {
        dashboardElementRepository.saveScreens(screensConfig.map { it.toModel() })
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

    override suspend fun saveTerminalConnection(data: TerminalConnectionModel): Int {

        when (data.connectionWay){
            1 -> serverConnection.setTypeConnection(TypeConnectionEnum.SIGNAL_R)
            2 -> serverConnection.setTypeConnection(TypeConnectionEnum.SOCKET)
            else -> serverConnection.setTypeConnection(TypeConnectionEnum.MOCK)
        }

        //storage local config preferences
        preferences.put(TERMINAL_IP, data.terminalIp)
        preferences.put(TERMINAL_PORT, data.port)
        preferences.put(TERMINAL_API, data.terminalApi)
        preferences.put(TIME_DELAY, data.timeDelay)
        preferences.put(VIDEO_FRAME, data.videoFrame)
        preferences.put(TEXT_SIZE_SCALE, data.textSizeScale)

        //storage images
        storageImages(data.files)

        return 0
    }

    private suspend fun storageImages(files: Map<String, String>?) {
        if (files.isNullOrEmpty()) return
        dashboardElementRepository.saveImages(
            files.map { (name, content) -> ImagesModel(fileName = name, fileContent = content) }
        )
    }

    override suspend fun getCurrentConfiguration(): List<ScreenDto> {
        val result = dashboardElementRepository.getAllScreens()
        return result.map { it.toDto() }
    }
}