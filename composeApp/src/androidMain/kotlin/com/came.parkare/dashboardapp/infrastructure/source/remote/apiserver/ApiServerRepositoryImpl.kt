package com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver

import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS_DELAY_TIME
import com.came.parkare.dashboardapp.config.constants.Constants.CONFIG_TYPE
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_API
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_IP
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.came.parkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.came.parkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.DeviceUtils
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.toDto
import com.came.parkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.came.parkare.dashboardapp.domain.repositories.local.ConfigTemplateRepository
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardDevicesRepository
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.domain.repositories.remote.ApiServerRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackErrorDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackLogDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel

class ApiServerRepositoryImpl(
    private val dashboardElementRepository: DashboardElementRepository,
    private val serverConnection: IServerConnection,
    private val configFileRepository: ConfigFileRepository,
    private val preferences: SharedPreferencesProvider,
    private val templateRepository: ConfigTemplateRepository,
    private val dashboardDevicesRepository: DashboardDevicesRepository,
    private val deviceUtils: DeviceUtils,
    private val appLogger: AppLogger
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

    override suspend fun getCurrentConnectionConfig(): ConnectionConfigDto {
        val terminalIp = preferences.get(TERMINAL_IP, "0.0.0.0")
        val port = preferences.get(TERMINAL_PORT, 9011)
        val terminalApi = preferences.get(TERMINAL_API, "")
        val apiPort = preferences.get(API_PORT, 2023)
        val timeDelay = preferences.get(TIME_DELAY, 4)
        val videoFrame = preferences.get(VIDEO_FRAME, false)
        val textSizeScale = preferences.get(TEXT_SIZE_SCALE, 10)
        val autoBrightness = preferences.get(AUTO_BRIGHTNESS, false)
        val autoBrightnessDelayTime = preferences.get(AUTO_BRIGHTNESS_DELAY_TIME, 120)

        val connectionWay = when (serverConnection.typeConnection.value){
            TypeConnectionEnum.SIGNAL_R -> 1
            TypeConnectionEnum.SOCKET -> 2
            else -> 0
        }

        //get images
        val images = dashboardElementRepository.getImages()

        return ConnectionConfigDto(
            connectionWay = connectionWay,
            terminalIp = terminalIp,
            port = port,
            terminalApi = terminalApi,
            apiPort = apiPort,
            timeDelay = timeDelay,
            videoFrame = videoFrame,
            textSizeScale = textSizeScale,
            autoBrightness = autoBrightness,
            activeLowBrightnessTime = autoBrightnessDelayTime,
            files = images.map { it.toDto() }
        )
    }

    override suspend fun saveScreenConfigType(type: Long): Boolean {
        preferences.put(CONFIG_TYPE, type)
        return true
    }

    override suspend fun getScreenConfigType(): Long {
        val currentConfig = templateRepository.getAll()
        return if (currentConfig.isNotEmpty())
            preferences.get(CONFIG_TYPE, currentConfig.first().id)
        else 0
    }

    override suspend fun saveConfiguratorLog(dto: TrackLogDto): Int {
        appLogger.trackConfiguratorLog(dto.tag, dto.message)
        return 0
    }

    override suspend fun saveConfiguratorException(dto: TrackErrorDto): Int {
        appLogger.trackConfiguratorError(dto.stackTrace,dto.localizedMessage)
        return 0
    }

    override suspend fun getAppVersion(): String {
        val version = deviceUtils.getAppVersion()
        return version?.versionName ?: "1.0"
    }
}