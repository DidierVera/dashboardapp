package com.cameparkare.dashboardapp.infrastructure.repositories.local

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.ImagesModel
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ImagesDao
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ScreenDao
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toModel

class DashboardElementRepositoryImpl(
    private val screenDao: ScreenDao,
    private val appLogger: AppLogger,
    private val imagesDao: ImagesDao

): DashboardElementRepository {
    override suspend fun saveScreens(items: List<ScreenModel>) {
        try {
            screenDao.deleteAllScreens()
            screenDao.insertAll(items.map{it.toEntity()})
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun deleteAll() {
        try {
            screenDao.deleteAllScreens()
        }catch (e: Exception) {
            appLogger.trackError(e)
        }
    }

    override suspend fun getScreenByDispatcher(dispatcherCode: Long): ScreenModel? {
        return try {
            val entity = screenDao.getScreenByDispatcher(dispatcherCode)
            entity?.toModel()
        }catch (e: Exception){
            appLogger.trackError(e)
            null
        }
    }

    override suspend fun getAllScreens(): List<ScreenModel> {
        return try {
            val entities = screenDao.getAll()
            entities.map { it.toModel() }
        }catch (e: Exception){
            appLogger.trackError(e)
            emptyList()
        }
    }

    override suspend fun saveImages(images: List<ImagesModel>) {
        try {
            val ids = imagesDao.insertAll(images.map{it.toEntity()})
            if (ids.isEmpty())
                throw Exception("Was no saved the images")
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun getImages(): List<ImagesModel> {
        return try {
            val entities = imagesDao.getAll()
            entities.map { it.toModel() }
        }catch (e: Exception){
            appLogger.trackError(e)
            emptyList()
        }
    }

    override suspend fun deleteImageById(id: Long) {
        try {
            imagesDao.deleteImage(id)
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }
}