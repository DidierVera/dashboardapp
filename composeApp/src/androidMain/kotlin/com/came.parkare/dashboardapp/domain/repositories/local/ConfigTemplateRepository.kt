package com.came.parkare.dashboardapp.domain.repositories.local

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel

interface ConfigTemplateRepository {
    suspend fun getAll(): List<ConfigTemplateModel>
    suspend fun saveTemplate(templateModel: ConfigTemplateModel) : Boolean
    suspend fun updateTemplate(templateModel: ConfigTemplateModel) : Boolean
    suspend fun deleteTemplate(templateModel: ConfigTemplateModel) : Boolean
    suspend fun getDefaultTemplates() : List<ConfigTemplateModel>
}