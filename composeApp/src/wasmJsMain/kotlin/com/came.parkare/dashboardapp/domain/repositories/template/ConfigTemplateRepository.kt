package com.came.parkare.dashboardapp.domain.repositories.template

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel

interface ConfigTemplateRepository {
    suspend fun getTemplates(ipAddress: String): ServiceResult<List<ConfigTemplateModel>>
    suspend fun getDefaultTemplates(ipAddress: String): ServiceResult<List<ConfigTemplateModel>>
    suspend fun editTemplate(ipAddress: String, model: ConfigTemplateModel): ServiceResult<ResponseStatusDto>
    suspend fun createTemplate(ipAddress: String, model: ConfigTemplateModel): ServiceResult<ResponseStatusDto>
    suspend fun deleteTemplate(ipAddress: String, model: ConfigTemplateModel): ServiceResult<ResponseStatusDto>
}