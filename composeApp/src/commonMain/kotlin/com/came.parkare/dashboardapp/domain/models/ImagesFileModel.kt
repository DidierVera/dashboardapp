package com.came.parkare.dashboardapp.domain.models

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ImageFileDto

data class ImagesFileModel(
    val id: Long = 0,
    val fileName: String?,
    val fileContent: String?
)

fun ImagesFileModel.toDto() : ImageFileDto {
    return ImageFileDto(
        id = id, fileContent = fileContent.orEmpty(), fileName = fileName.orEmpty()
    )
}