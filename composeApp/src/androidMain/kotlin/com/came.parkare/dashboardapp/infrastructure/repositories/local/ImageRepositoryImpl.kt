package com.came.parkare.dashboardapp.infrastructure.repositories.local

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.repositories.local.ImageRepository
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.ImagesDao
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.toModel

class ImageRepositoryImpl(private val imagesDao: ImagesDao): ImageRepository {
    override suspend fun getImageDataByName(filename: String): ImagesFileModel? {
        return imagesDao.getByName(filename)?.toModel()
    }
}