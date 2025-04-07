package com.came.parkare.dashboardapp.domain.repositories.local

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel

interface DashboardElementRepository {
    suspend fun saveScreens(items: List<ScreenModel>)
    suspend fun deleteAll()
    suspend fun getScreenByDispatcher(dispatcherCode: Long): ScreenModel?
    suspend fun getAllScreens(): List<ScreenModel>
    suspend fun saveImages(images: List<ImagesFileModel>)
    suspend fun getImages(): List<ImagesFileModel>
    suspend fun deleteAllImages()
    suspend fun getImageByName(fileName: String): ImagesFileModel?
    suspend fun deleteImageById(id: Long)
    suspend fun replaceAllImages(images: List<ImagesFileModel>)
}