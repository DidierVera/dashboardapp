package com.came.parkare.dashboardapp.domain.repositories.local

import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel

interface DashboardElementRepository {
    suspend fun saveScreens(items: List<ScreenModel>)
    suspend fun deleteAll()
    suspend fun getScreenByDispatcher(dispatcherCode: Long): ScreenModel?
    suspend fun getAllScreens(): List<ScreenModel>
    suspend fun saveImages(images: List<ResourceFileModel>)
    suspend fun getImages(): List<ResourceFileModel>
    suspend fun deleteAllImages()
    suspend fun getImageByName(fileName: String): ResourceFileModel?
    suspend fun deleteImageById(id: Long)
    suspend fun replaceAllImages(images: List<ResourceFileModel>)
}