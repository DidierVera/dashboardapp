package com.came.parkare.dashboardapp.infrastructure.source.external.dto.device

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageFileDto(
    val fileName: String,
    val fileContent: String,
    val id: Long = 0
)

fun ImageFileDto.toModel(): ImagesFileModel {
    return ImagesFileModel(
        fileContent = fileContent, fileName = fileName, id = id
    )
}