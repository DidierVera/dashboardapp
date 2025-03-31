package com.cameparkare.dashboardapp.infrastructure.repositories.external

import com.cameparkare.dashboardapp.config.constants.Constants.FTP_APP_PASSWORD
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_APP_PORT
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_APP_USER
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PASSWORD
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PORT
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_DEVICE_USER
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.repositories.external.FtpServerFileRepository
import com.cameparkare.dashboardapp.infrastructure.source.external.ConfigFileDao
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.FtpServerConnectionDto
import com.cameparkare.dashboardapp.infrastructure.source.mocks.ConfigFileMock

class FtpServerFileImpl(
    private val preferences: SharedPreferencesProvider,
    private val configFileDao: ConfigFileDao,
    private val appLogger: AppLogger
) : FtpServerFileRepository {
    override suspend fun readFtpServerConfiguration(): ServiceResult<Boolean> {
        appLogger.trackLog("readFtpServerConfig: ", "_________")
        when (val dataFromFile =
            configFileDao.readJsonFromFile<FtpServerConnectionDto>(
                filename = "ftp_connection.json",
                defaultValues = ConfigFileMock.getFtpConfigValues())
        ) {
            is ServiceResult.Error -> return ServiceResult.Error(dataFromFile.error)
            is ServiceResult.Success -> {
                val data = dataFromFile.data ?: return ServiceResult.Error(ErrorTypeClass.WrongConfigFile)

                //storage ftp config
                saveFtpServerConfig(data)

                appLogger.trackLog("readFtpServerConfiguration: ", "Success")
                return ServiceResult.Success(true)
            }
        }
    }

    override suspend fun writeFtpServerConfiguration(newData: FtpServerConnectionDto): ServiceResult<Boolean> {
        try {
            val result = configFileDao.writeJsonToFile(filename = "ftp_connection.json",
                content = newData)
            return when(result){
                is ServiceResult.Error -> ServiceResult.Error(result.error)
                is ServiceResult.Success -> {
                    saveFtpServerConfig(newData)
                    ServiceResult.Success(true)
                }
            }
        }catch (e: Exception){
            return ServiceResult.Error(ErrorTypeClass.GeneralException(messageError = e.message))
        }
    }

    private fun saveFtpServerConfig(data: FtpServerConnectionDto) {
        preferences.put(FTP_DEVICE_PORT, data.deviceRoot.port)
        preferences.put(FTP_DEVICE_USER, data.deviceRoot.username)
        preferences.put(FTP_DEVICE_PASSWORD, data.deviceRoot.password)
        preferences.put(FTP_APP_PORT, data.appRoot.port)
        preferences.put(FTP_APP_USER, data.appRoot.username)
        preferences.put(FTP_APP_PASSWORD, data.appRoot.password)
    }
}