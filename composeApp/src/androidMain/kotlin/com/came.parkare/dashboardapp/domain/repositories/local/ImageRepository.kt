package com.came.parkare.dashboardapp.domain.repositories.local

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel

interface ImageRepository {
    suspend fun getImageDataByName(filename: String): ImagesFileModel?
}