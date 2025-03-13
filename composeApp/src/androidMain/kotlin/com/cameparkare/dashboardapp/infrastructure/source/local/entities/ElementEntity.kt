package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.components.*
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.ELEMENTS_TABLE_NAME

sealed class ElementEntity {
    abstract val type: String
    data class BoxEntity(
        val data: BoxDataEntity,
        override val type: String = "Box"
    ) : ElementEntity()

    data class SpacerEntity(
        val data: SpacerDataEntity,
        override val type: String = "Spacer"
    ) : ElementEntity()

    data class ColumnEntity(
        val data: ColumnDataEntity,
        override val type: String = "Column"
    ) : ElementEntity()

    data class TextEntity(
        val data: TextDataEntity,
        override val type: String = "Text"
    ) : ElementEntity()

    data class RowEntity(
        val data: RowDataEntity,
        override val type: String = "Row"
    ) : ElementEntity()

    data class ImageEntity(
        val data: ImageDataEntity,
        override val type: String = "Image"
    ) : ElementEntity()

    data class VideoEntity(
        val data: VideoDataEntity,
        override val type: String = "Video"
    ) : ElementEntity()
}