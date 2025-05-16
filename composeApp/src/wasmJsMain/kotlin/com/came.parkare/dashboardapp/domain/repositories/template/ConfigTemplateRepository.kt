package com.came.parkare.dashboardapp.domain.repositories.template

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel

interface ConfigTemplateRepository {
    suspend fun getTemplates(ipAddress: String): List<ConfigTemplateModel>
    suspend fun editTemplate(ipAddress: String, model: ConfigTemplateModel): ResponseStatusDto
    suspend fun createTemplate(ipAddress: String, model: ConfigTemplateModel): ResponseStatusDto
    suspend fun deleteTemplate(ipAddress: String, model: ConfigTemplateModel): ResponseStatusDto
}