package com.cameparkare.dashboardapp.domain.repositories.local

import com.cameparkare.dashboardapp.domain.models.ImagesFileModel
import com.cameparkare.dashboardapp.domain.models.ScreenModel

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