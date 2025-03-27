package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.domain.models.ImagesModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.IMAGES_TABLE_NAME

@Entity(tableName = IMAGES_TABLE_NAME)
data class ImagesFilesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fileName: String?,
    val fileContent: String?
)

fun ImagesModel.toEntity(): ImagesFilesEntity {
    return ImagesFilesEntity(
        fileName = fileName, fileContent = fileContent
    )
}

fun ImagesFilesEntity.toModel(): ImagesModel {
    return ImagesModel(
        fileName = fileName, fileContent = fileContent
    )
}