package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import java.io.FileNotFoundException

class ConnectionConfig(
    private val configFileRepository: ConfigFileRepository,
    private val appLogger: AppLogger
) {
    suspend fun invoke(): ServiceResult<Boolean> {
        return try {
            appLogger.trackLog("Use case Load connection config", "============")
            when(val result = configFileRepository.getConnectionConfig()){
                is ServiceResult.Error -> ServiceResult.Error(result.error)
                is ServiceResult.Success -> ServiceResult.Success(result.data)
            }
        }
        catch (e: FileNotFoundException){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.CanNoAccessToConfigFile)
        }
        catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.WrongConfigFile)
        }
    }
}