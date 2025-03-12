package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.components.*
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.ELEMENTS_TABLE_NAME

@Entity(tableName = ELEMENTS_TABLE_NAME)
sealed class ElementEntity {
    @PrimaryKey(autoGenerate = true) val id: Long = 0
    abstract val type: String

    @Entity(tableName = "box_table")
    data class BoxEntity(
        val data: BoxDataEntity,
        override val type: String = "Box"
    ) : ElementEntity()

    @Entity(tableName = "spacer_table")
    data class SpacerEntity(
        val data: SpacerDataEntity,
        override val type: String = "Spacer"
    ) : ElementEntity()

    @Entity(tableName = "column_table")
    data class ColumnEntity(
        val data: ColumnDataEntity,
        override val type: String = "Column"
    ) : ElementEntity()

    @Entity(tableName = "text_table")
    data class TextEntity(
        val data: TextDataEntity,
        override val type: String = "Text"
    ) : ElementEntity()

    @Entity(tableName = "row_table")
    data class RowEntity(
        val data: RowDataEntity,
        override val type: String = "Row"
    ) : ElementEntity()

    @Entity(tableName = "image_table")
    data class ImageEntity(
        val data: ImageDataEntity,
        override val type: String = "Image"
    ) : ElementEntity()

    @Entity(tableName = "video_table")
    data class VideoEntity(
        val data: VideoDataEntity,
        override val type: String = "Video"
    ) : ElementEntity()
}