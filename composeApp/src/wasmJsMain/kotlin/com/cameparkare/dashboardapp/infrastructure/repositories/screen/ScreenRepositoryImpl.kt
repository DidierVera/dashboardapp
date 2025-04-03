package com.cameparkare.dashboardapp.infrastructure.repositories.screen

import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.source.services.base.HttpClient

class ScreenRepositoryImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger
): ScreenRepository {
    override suspend fun saveScreens(
        ipAddress: String,
        screens: List<ScreenDto>
    ): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, List<ScreenDto>>("http://$ipAddress:2023/api/screen/save", screens)
            ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun getScreens(ipAddress: String): ServiceResult<List<ScreenDto>> {
        return try {
            val screenConfig = httpClient.get<List<ScreenDto>>("http://$ipAddress:2023/api/screen/get")
            ServiceResult.Success(screenConfig)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun shareScreenConfig(
        ipDestinations: List<String>,
        screens: List<ScreenDto>
    ): ServiceResult<ResponseStatusDto> {
        TODO("Not yet implemented")
    }

}