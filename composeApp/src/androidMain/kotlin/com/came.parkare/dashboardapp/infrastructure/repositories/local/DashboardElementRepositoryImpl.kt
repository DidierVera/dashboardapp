package com.came.parkare.dashboardapp.infrastructure.repositories.local

import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.ImagesDao
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.ScreenDao
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.toEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
        return withContext(Dispatchers.IO) {
            try {
                val entities = screenDao.getAll()
                appLogger.trackLog("Repository", "Obtained ${entities.size} screens from DB")
                entities.map { it.toModel() }
            } catch (e: Exception) {
                appLogger.trackError(e)
                appLogger.trackLog("Repository", "Error getting screens: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun saveImages(images: List<ImagesFileModel>) {
        try {
            val ids = imagesDao.insertAll(images.map{it.toEntity()})
            if (ids.isEmpty())
                throw Exception("Was no saved the images")
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun getImages(): List<ImagesFileModel> {
        return try {
            val entities = imagesDao.getAll()
            entities.map { it.toModel() }
        }catch (e: Exception){
            appLogger.trackError(e)
            emptyList()
        }
    }

    override suspend fun deleteAllImages() {
        try {
            imagesDao.deleteAllImages()
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun getImageByName(fileName: String): ImagesFileModel? {
        return try {
            val entity = imagesDao.getByName(fileName)
            entity?.toModel()
        }catch (e: Exception){
            appLogger.trackError(e)
            null
        }
    }

    override suspend fun deleteImageById(id: Long) {
        try {
            imagesDao.deleteImage(id)
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun replaceAllImages(images: List<ImagesFileModel>) {
        val entities = images.map { it.toEntity() }
        imagesDao.replaceAllImages(entities)
    }
}