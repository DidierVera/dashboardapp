package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import com.came.parkare.dashboardapp.domain.repositories.local.ImageRepository

class GetImageFromDbByName(
    private val imageRepository: ImageRepository
) {
    suspend fun invoke(filename: String): ResourceFileModel? {
        return imageRepository.getImageDataByName(filename)
    }
}