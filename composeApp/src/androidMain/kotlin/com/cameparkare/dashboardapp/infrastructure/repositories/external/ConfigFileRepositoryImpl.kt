package com.cameparkare.dashboardapp.infrastructure.repositories.external

import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_API
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_IP
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.cameparkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.cameparkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.cameparkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.models.ImagesModel
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.DashboardItemDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.toModel
import com.cameparkare.dashboardapp.infrastructure.source.external.ConfigFileDao
import com.cameparkare.dashboardapp.infrastructure.source.mocks.ConfigFileMock

class ConfigFileRepositoryImpl(
    private val configFileDao: ConfigFileDao,
    private val preferences: SharedPreferencesProvider,
    private val dashboardElementRepository: DashboardElementRepository,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
): ConfigFileRepository {
    override suspend fun getConnectionConfig(): ServiceResult<Boolean> {
        appLogger.trackLog("getConnectionConfig: ", "_________")
        when (val dataFromFile = configFileDao.readJsonFromFile<ConnectionConfigDto>(
            filename = "connection_config.json",
            defaultValues = ConfigFileMock.getConnectionConfiguration()
        )){

            is ServiceResult.Error -> return ServiceResult.Error(dataFromFile.error)
            is ServiceResult.Success -> {
                val data = dataFromFile.data ?: return ServiceResult.Error(ErrorTypeClass.WrongConfigFile)

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

                return ServiceResult.Success(true)
            }
        }
    }

    private suspend fun storageImages(files: Map<String, String>?) {
        if (files.isNullOrEmpty()) return
        dashboardElementRepository.saveImages(
            files.map { (name, content) -> ImagesModel(fileName = name, fileContent = content) }
        )
    }

    override suspend fun getFileConfiguration(): ServiceResult<List<ScreenModel>> {
        appLogger.trackLog("getFileConfiguration: ", "_________")
        when (val dataFromFile =
            configFileDao.readJsonFromFile<DashboardItemDto>(
                filename = "config_dashboard.json",
                defaultValues = ConfigFileMock.getConfigFile())
        ){
            is ServiceResult.Error -> return ServiceResult.Error(dataFromFile.error)
            is ServiceResult.Success -> {
                val data = dataFromFile.data ?: return ServiceResult.Error(ErrorTypeClass.WrongConfigFile)

                //delete and storage screens and elements
                storageScreensAndElements(data.screens)
                val screens = dashboardElementRepository.getAllScreens()
                appLogger.trackLog("getFileConfiguration: ", "Success")
                return ServiceResult.Success(screens)
            }
        }
    }

    private suspend fun storageScreensAndElements(screens: List<ScreenDto>) {
        dashboardElementRepository.deleteAll()
        dashboardElementRepository.saveScreens(screens.map { it.toModel() })
    }
}