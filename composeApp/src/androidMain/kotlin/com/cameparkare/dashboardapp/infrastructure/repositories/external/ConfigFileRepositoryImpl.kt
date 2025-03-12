package com.cameparkare.dashboardapp.infrastructure.repositories.external

import android.provider.Telephony.Carriers.PORT
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_BOTTOM
import com.cameparkare.dashboardapp.config.constants.Constants.IP_ADDRESS
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_LEFT
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_RIGHT
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_TOP
import com.cameparkare.dashboardapp.config.constants.Constants.SIGNAL_R_URI
import com.cameparkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.cameparkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.cameparkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.DashboardItemDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.toModel
import com.cameparkare.dashboardapp.infrastructure.source.external.ConfigFileDao
import com.cameparkare.dashboardapp.infrastructure.source.mocks.ConfigFileMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConfigFileRepositoryImpl(
    private val configFileDao: ConfigFileDao,
    private val preferences: SharedPreferencesProvider,
    private val dashboardElementRepository: DashboardElementRepository,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
): ConfigFileRepository {

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
                var checkConfiguration = false

                //storage local config preferences
                data.socketIp?.let {
                    checkConfiguration = true
                    appLogger.trackLog("CONNECTION MODE: ", TypeConnectionEnum.SOCKET.toString())
                    appLogger.trackLog("$IP_ADDRESS: ", it)
                    serverConnection.setTypeConnection(TypeConnectionEnum.SOCKET)
                    preferences.put(IP_ADDRESS, it) }
                data.socketPort?.let {
                    appLogger.trackLog("$PORT: ", "$it")
                    preferences.put(PORT, it)
                }
                data.signalRUrl?.let {
                    checkConfiguration = true
                    appLogger.trackLog("CONNECTION MODE: ", TypeConnectionEnum.SIGNAL_R.toString())
                    appLogger.trackLog("$SIGNAL_R_URI: ", it)
                    appLogger.trackLog("$TIME_DELAY: ", "${ data.timeDelay }")
                    serverConnection.setTypeConnection(TypeConnectionEnum.SIGNAL_R)
                    preferences.put(SIGNAL_R_URI, it) }

                if (!checkConfiguration){
                    appLogger.trackLog("CONNECTION MODE: ", "MOCK")
                    serverConnection.setTypeConnection(TypeConnectionEnum.MOCK)
                }
                data.timeDelay?.let {
                    preferences.put(TIME_DELAY, it)
                }
                data.videoFrame?.let {
                    preferences.put(VIDEO_FRAME, it)
                }
                data.marginTop?.let {
                    preferences.put(MARGIN_TOP, it)
                }
                data.marginBottom?.let {
                    preferences.put(MARGIN_BOTTOM, it)
                }
                data.marginRight?.let {
                    preferences.put(MARGIN_RIGHT, it)
                }
                data.marginLeft?.let {
                    preferences.put(MARGIN_LEFT, it)
                }
                data.textSizeScale?.let {
                    preferences.put(TEXT_SIZE_SCALE, it)
                }

                //storage screens and elements
                storageScreensAndElements(data.screens)
                val screens = dashboardElementRepository.getAllScreens()
                appLogger.trackLog("getFileConfiguration: ", "Success")
                return ServiceResult.Success(screens)
            }
        }
    }

    private suspend fun storageScreensAndElements(screens: List<ScreenDto>) {
        dashboardElementRepository.saveScreens(screens.map { it.toModel() })
    }
}