package com.came.parkare.dashboardapp.infrastructure.source.external.dto.device

import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import kotlinx.serialization.Serializable

@Serializable
data class ResourceFileDto(
    val fileName: String,
    val fileContent: String,
    val fileContentArray: ByteArray? = null,
    val id: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ResourceFileDto

        if (fileName != other.fileName) return false
        if (fileContent != other.fileContent) return false
        if (fileContentArray != null) {
            if (other.fileContentArray == null) return false
            if (!fileContentArray.contentEquals(other.fileContentArray)) return false
        } else if (other.fileContentArray != null) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + fileContent.hashCode()
        result = 31 * result + (fileContentArray?.contentHashCode() ?: 0)
        result = 31 * result + id.hashCode()
        return result
    }
}

fun ResourceFileDto.toModel(): ResourceFileModel {
    return ResourceFileModel(
        fileContent = fileContent, fileName = fileName, id = id
    )
}