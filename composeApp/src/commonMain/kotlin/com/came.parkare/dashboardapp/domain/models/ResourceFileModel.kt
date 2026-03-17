package com.came.parkare.dashboardapp.domain.models

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto

data class ResourceFileModel(
    val id: Long = 0,
    val fileName: String?,
    val fileContent: String?,
    val fileContentArray: ByteArray? = null,
)

fun ResourceFileModel.toDto() : ResourceFileDto {
    return ResourceFileDto(
        id = id, fileContent = fileContent.orEmpty(), fileName = fileName.orEmpty(), fileContentArray = fileContentArray
    )
}