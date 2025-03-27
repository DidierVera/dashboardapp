package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import com.cameparkare.dashboardapp.infrastructure.source.local.entities.components.*
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