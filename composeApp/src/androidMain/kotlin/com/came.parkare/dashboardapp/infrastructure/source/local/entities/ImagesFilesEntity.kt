package com.came.parkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.IMAGES_TABLE_NAME

@Entity(tableName = IMAGES_TABLE_NAME)
data class ImagesFilesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fileName: String?,
    val fileContent: String?
)

fun ResourceFileModel.toEntity(): ImagesFilesEntity {
    return ImagesFilesEntity(
        id = id, fileName = fileName, fileContent = fileContent
    )
}

fun ImagesFilesEntity.toModel(): ResourceFileModel {
    return ResourceFileModel(
        id = id, fileName = fileName, fileContent = fileContent
    )
}