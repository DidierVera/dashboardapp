package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.components.*
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.ELEMENTS_TABLE_NAME
import kotlinx.serialization.Serializable

@Serializable
sealed class ElementEntity {
    @Serializable
    data class BoxEntity(
        val data: BoxDataEntity,
    ) : ElementEntity()

    @Serializable
    data class SpacerEntity(
        val data: SpacerDataEntity,
    ) : ElementEntity()

    @Serializable
    data class ColumnEntity(
        val data: ColumnDataEntity,
    ) : ElementEntity()

    @Serializable
    data class TextEntity(
        val data: TextDataEntity,
    ) : ElementEntity()

    @Serializable
    data class RowEntity(
        val data: RowDataEntity,
    ) : ElementEntity()

    @Serializable
    data class ImageEntity(
        val data: ImageDataEntity,
    ) : ElementEntity()

    @Serializable
    data class VideoEntity(
        val data: VideoDataEntity,
    ) : ElementEntity()
}