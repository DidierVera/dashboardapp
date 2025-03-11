package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import java.io.FileNotFoundException

class InitConfiguration (
    private val configFileRepository: ConfigFileRepository,
    private val appLogger: AppLogger
) {
    suspend fun invoke(): ServiceResult<List<ScreenModel>> {
        return try {
            appLogger.trackLog("Use case Load initial configuration", "============")
            when(val result = configFileRepository.getFileConfiguration()){
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