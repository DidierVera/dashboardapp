package com.came.parkare.dashboardapp.infrastructure.source.external.dto.device

import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import kotlinx.serialization.Serializable

@Serializable
data class ResourceFileDto(
    val fileName: String,
    val fileContent: String,
    val id: Long = 0
)

fun ResourceFileDto.toModel(): ResourceFileModel {
    return ResourceFileModel(
        fileContent = fileContent, fileName = fileName, id = id
    )
}