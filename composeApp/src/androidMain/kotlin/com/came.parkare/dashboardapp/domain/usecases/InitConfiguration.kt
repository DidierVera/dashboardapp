package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import java.io.FileNotFoundException

class InitConfiguration (
    private val getScreenConfigurations: GetScreenConfigurations,
    private val connectionConfig: ConnectionConfig,
    private val ftpServerConfiguration: FtpServerConfiguration,
    private val appLogger: AppLogger
) {
    suspend fun invoke(): ServiceResult<Int> {
        return try {
            appLogger.trackLog("Use case Load initial configuration", "============")
            getFtpConfig()
            getScreenConfig()
            getConnectionConfig()
        } catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(messageError = e.message))
        }
    }

    private suspend fun getScreenConfig(): ServiceResult<Int>{
        //screens config initialization
        return when (val initConfigResult = getScreenConfigurations.invoke()) {
            is ServiceResult.Error -> ServiceResult.Error(initConfigResult.error)
            is ServiceResult.Success -> ServiceResult.Success(0)
        }
    }

    private suspend fun getFtpConfig(): ServiceResult<Int>{
        //ftp server config initialization
        return when(val ftpServerConfigResult = ftpServerConfiguration.invoke()){
            is ServiceResult.Error -> ServiceResult.Error(ftpServerConfigResult.error)
            else -> ServiceResult.Success(0)
        }
    }

    private suspend fun getConnectionConfig(): ServiceResult<Int>{
        //connection config initialization
        return when (val connectionResult = connectionConfig.invoke()){
            is ServiceResult.Error -> ServiceResult.Error(connectionResult.error)
            is ServiceResult.Success -> ServiceResult.Success(0)
        }
    }
}