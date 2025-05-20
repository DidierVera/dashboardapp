package com.came.parkare.dashboardapp.infrastructure.repositories.template

import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.DELETE_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.GET_DEFAULT_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SAVE_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.SSL_PROTOCOL
import com.came.parkare.dashboardapp.config.constants.ApiRequestUri.UPDATE_CONFIG_TEMPLATE
import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.toDto
import com.came.parkare.dashboardapp.domain.repositories.template.ConfigTemplateRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ConfigTemplateDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.infrastructure.source.services.base.HttpClient

class ConfigTemplateRepositoryImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger,
    private val preferences: WasmSharedPreferencesProvider
): ConfigTemplateRepository {

    private val apiPort = preferences.get(API_PORT, 2023)

    override suspend fun getTemplates(ipAddress: String): ServiceResult<List<ConfigTemplateModel>> {
        return try {
            val templates = httpClient.get<List<ConfigTemplateDto>>("$SSL_PROTOCOL$ipAddress:$apiPort$GET_CONFIG_TEMPLATE")
            ServiceResult.Success(templates.map { it.toModel() })
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun getDefaultTemplates(ipAddress: String): ServiceResult<List<ConfigTemplateModel>> {
        return try {
            val templates = httpClient.get<List<ConfigTemplateDto>>("$SSL_PROTOCOL$ipAddress:$apiPort$GET_DEFAULT_CONFIG_TEMPLATE")
            ServiceResult.Success(templates.map { it.toModel() })
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun editTemplate(
        ipAddress: String,
        model: ConfigTemplateModel,
    ): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, ConfigTemplateDto>("$SSL_PROTOCOL$ipAddress:$apiPort$UPDATE_CONFIG_TEMPLATE", model.toDto())
            return ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun createTemplate(
        ipAddress: String,
        model: ConfigTemplateModel,
    ): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, ConfigTemplateDto>("$SSL_PROTOCOL$ipAddress:$apiPort$SAVE_CONFIG_TEMPLATE", model.toDto())
            return ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }

    override suspend fun deleteTemplate(
        ipAddress: String,
        model: ConfigTemplateModel,
    ): ServiceResult<ResponseStatusDto> {
        return try {
            val result = httpClient.post<ResponseStatusDto, ConfigTemplateDto>("$SSL_PROTOCOL$ipAddress:$apiPort$DELETE_CONFIG_TEMPLATE", model.toDto())
            return ServiceResult.Success(result)
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }
}