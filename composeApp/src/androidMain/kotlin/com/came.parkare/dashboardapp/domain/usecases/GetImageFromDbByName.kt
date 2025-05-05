package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.repositories.local.ImageRepository

class GetImageFromDbByName(
    private val imageRepository: ImageRepository
) {
    suspend fun invoke(filename: String): ImagesFileModel? {
        return imageRepository.getImageDataByName(filename)
    }
}