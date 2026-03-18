package com.came.parkare.dashboardapp.infrastructure.repositories.external

import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS_DELAY_TIME
import com.came.parkare.dashboardapp.config.constants.Constants.RESET_COUNTER_DELAY_TIME
import com.came.parkare.dashboardapp.config.constants.Constants.SHOW_COUNTER
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_API
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_IP
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.came.parkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.came.parkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.ConnectionConfigModel
import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.toDto
import com.came.parkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.ConfigFileDao
import com.came.parkare.dashboardapp.infrastructure.source.external.FontFileDao
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.infrastructure.source.mocks.ConfigFileMock

class ConfigFileRepositoryImpl(
    private val configFileDao: ConfigFileDao,
    private val fontFileDao: FontFileDao,
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

                saveConnectionConfig(data)

                return ServiceResult.Success(true)
            }
        }
    }

    private suspend fun saveConnectionConfig(data: ConnectionConfigDto) {
        //storage local config preferences
        preferences.put(TERMINAL_IP, data.terminalIp)
        preferences.put(TERMINAL_PORT, data.port)
        preferences.put(TERMINAL_API, data.terminalApi)
        preferences.put(API_PORT, data.apiPort)
        preferences.put(TIME_DELAY, data.timeDelay)
        preferences.put(VIDEO_FRAME, data.videoFrame)
        preferences.put(TEXT_SIZE_SCALE, data.textSizeScale)
        preferences.put(AUTO_BRIGHTNESS, data.autoBrightness)
        preferences.put(AUTO_BRIGHTNESS_DELAY_TIME, data.activeLowBrightnessTime)
        preferences.put(SHOW_COUNTER, data.showCarCounter)
        preferences.put(RESET_COUNTER_DELAY_TIME, data.carCounterReset)

        when (data.connectionWay){
            1 -> serverConnection.setTypeConnection(TypeConnectionEnum.SIGNAL_R)
            2 -> serverConnection.setTypeConnection(TypeConnectionEnum.SOCKET)
            else -> serverConnection.setTypeConnection(TypeConnectionEnum.MOCK)
        }
    }

    private suspend fun storageImages(files: List<ResourceFileModel>?) {
        if (files.isNullOrEmpty()) {
            dashboardElementRepository.deleteAllImages()
            return
        }

        // Otherwise, replace all images in a single transaction
        dashboardElementRepository.replaceAllImages(files)
    }

    override suspend fun getFileConfiguration(): ServiceResult<Boolean> {
        appLogger.trackLog("getFileConfiguration: ", "_________")
        when (val dataFromFile =
            configFileDao.readJsonFromFile<List<ScreenDto>>(
                filename = "dashboard_screens.json",
                defaultValues = ConfigFileMock.getConfigFile())
        ){
            is ServiceResult.Error -> return ServiceResult.Error(dataFromFile.error)
            is ServiceResult.Success -> {
                val data = dataFromFile.data ?: return ServiceResult.Error(ErrorTypeClass.WrongConfigFile)

                //delete and storage screens and elements
                storageScreensAndElements(data.map { it.toModel() })
                appLogger.trackLog("getFileConfiguration: ", "Success")
                return ServiceResult.Success(true)
            }
        }
    }

    override suspend fun getDefaultImages(): ServiceResult<Boolean> {
        appLogger.trackLog("getDefaultImages", "----starting----")
        when(val images = configFileDao.readJsonFromFile<List<ResourceFileDto>?>(
            filename = "default_images.json", defaultValues = ConfigFileMock.getDefaultImages()
        )){
            is ServiceResult.Error -> return ServiceResult.Error(images.error)
            is ServiceResult.Success -> {
                val data = images.data
                storageImages(data?.map { it.toModel() })
                appLogger.trackLog("getDefaultImages: ", "----Success----")
                return ServiceResult.Success(true)
            }
        }
    }

    override suspend fun writeImages(newData: List<ResourceFileModel>): ServiceResult<Boolean> {
        try {
            val result = configFileDao.writeJsonToFile(filename = "default_images.json",
                content = newData.map { it.toDto() })
            return when(result){
                is ServiceResult.Error -> ServiceResult.Error(result.error)
                is ServiceResult.Success -> {
                    storageImages(newData)
                    ServiceResult.Success(true)
                }
            }
        }catch (e: Exception){
            return ServiceResult.Error(ErrorTypeClass.GeneralException(messageError = e.message))
        }
    }

    override suspend fun getFont(): ServiceResult<ResourceFileModel> {
        TODO("Not yet implemented")
    }

    override suspend fun writeFont(fileName: String, contentData: ByteArray): ServiceResult<Boolean> {
        try {
            val result = fontFileDao.saveFontFile(fileName = fileName, fontData = contentData, true)
            return when(result){
                false -> ServiceResult.Error(ErrorTypeClass.GeneralException("Could no storage the file, check it and try again"))
                true -> {
                    ServiceResult.Success(true)
                }
            }
        }catch (e: Exception){
            appLogger.trackError(e)
            return ServiceResult.Error(ErrorTypeClass.GeneralException(messageError = e.message))
        }
    }

    override suspend fun writeScreensConfig(newData: List<ScreenModel>): ServiceResult<Boolean> {
        try {
            val result = configFileDao.writeJsonToFile(filename = "dashboard_screens.json",
                content = newData.map { it.toDto() })
            return when(result){
                is ServiceResult.Error -> ServiceResult.Error(result.error)
                is ServiceResult.Success -> {
                    storageScreensAndElements(newData)
                    ServiceResult.Success(true)
                }
            }
        }catch (e: Exception){
            return ServiceResult.Error(ErrorTypeClass.GeneralException(messageError = e.message))
        }
    }

    override suspend fun writeConnectionConfig(newData: ConnectionConfigModel): ServiceResult<Boolean> {
        try {
            val result = configFileDao.writeJsonToFile(filename = "connection_config.json",
                content = newData.toDto())
            return when(result){
                is ServiceResult.Error -> ServiceResult.Error(result.error)
                is ServiceResult.Success -> {
                    saveConnectionConfig(newData.toDto())
                    ServiceResult.Success(true)
                }
            }
        }catch (e: Exception){
            return ServiceResult.Error(ErrorTypeClass.GeneralException(messageError = e.message))
        }
    }

    private suspend fun storageScreensAndElements(screens: List<ScreenModel>) {
        dashboardElementRepository.deleteAll()
        dashboardElementRepository.saveScreens(screens)
        val storedScreens = dashboardElementRepository.getAllScreens()
        serverConnection.setScreensList(storedScreens)
    }
}