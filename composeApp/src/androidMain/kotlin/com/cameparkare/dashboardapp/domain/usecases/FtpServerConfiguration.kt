package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.repositories.external.FtpServerFileRepository
import java.io.FileNotFoundException

class FtpServerConfiguration (
    private val appLogger: AppLogger,
    private val repository: FtpServerFileRepository
) {
    suspend fun invoke(): ServiceResult<Boolean> {
        try {
            when(val result = repository.readFtpServerConfiguration()){
                is ServiceResult.Error -> return ServiceResult.Error(result.error)
                is ServiceResult.Success -> {
                    //Storage in preferences the ftp config
                    return ServiceResult.Success(true)
                }
            }
        }
        catch (e: FileNotFoundException){
            appLogger.trackError(e)
            return ServiceResult.Error(ErrorTypeClass.CanNoAccessToConfigFile)
        }
        catch (e: Exception){
            appLogger.trackError(e)
            return ServiceResult.Error(ErrorTypeClass.WrongConfigFile)
        }
    }
}