package com.came.parkare.dashboardapp.infrastructure.repositories.local

import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.repositories.local.ConfigTemplateRepository
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.ConfigTemplateDao
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.toEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.toModel

class ConfigTemplateRepositoryImpl(
    private val configTemplateDao: ConfigTemplateDao,
    private val appLogger: AppLogger
): ConfigTemplateRepository {
    override suspend fun getAll(): List<ConfigTemplateModel> {
        return try {
             configTemplateDao.getAllTemplates().map { it.toModel() }
        }catch (e: Exception){
            appLogger.trackError(e)
            emptyList()
        }
    }

    override suspend fun saveTemplate(templateModel: ConfigTemplateModel): Boolean {
        try {
            configTemplateDao.insertTemplate(templateModel.toEntity())
            return true
        }catch (e: Exception){
            appLogger.trackError(e)
            return false
        }
    }

    override suspend fun updateTemplate(templateModel: ConfigTemplateModel): Boolean {
        try {
            configTemplateDao.updateTemplate(templateModel.toEntity())
            return true
        }catch (e: Exception){
            appLogger.trackError(e)
            return false
        }
    }

    override suspend fun deleteTemplate(templateModel: ConfigTemplateModel): Boolean {
        try {
            configTemplateDao.deleteTemplate(templateModel.toEntity())
            return true
        }catch (e: Exception){
            appLogger.trackError(e)
            return false
        }
    }
}