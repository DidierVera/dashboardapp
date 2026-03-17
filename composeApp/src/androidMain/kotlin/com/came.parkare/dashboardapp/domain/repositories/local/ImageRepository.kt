package com.came.parkare.dashboardapp.domain.repositories.local

import com.came.parkare.dashboardapp.domain.models.ResourceFileModel

interface ImageRepository {
    suspend fun getImageDataByName(filename: String): ResourceFileModel?
}