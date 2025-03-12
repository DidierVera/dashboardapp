package com.cameparkare.dashboardapp.infrastructure.source.local.entities.components

import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.VideoDataModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity

data class VideoDataEntity(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val fileName: String? = null,
    val folderPath: String? = null,
    val style: CommonStyleEntity
)

fun ElementModel.VideoModel.toEntity(): ElementEntity.VideoEntity {
    return ElementEntity.VideoEntity(
        data = VideoDataEntity(
            validValue = data.validValue,
            dataKey = data.dataKey,
            ditTypeCode = data.ditTypeCode,
            style = data.style.toEntity(),
            folderPath = data.folderPath,
            fileName = data.fileName,
            dashboardItemId = data.dashboardItemId
        )
    )
}

fun VideoDataEntity.toModel(): ElementModel.VideoModel {
    return ElementModel.VideoModel(
        data = VideoDataModel(
            validValue = validValue,
            dataKey = dataKey,
            ditTypeCode = ditTypeCode,
            style = style.toModel(),
            folderPath = folderPath,
            fileName = fileName,
            dashboardItemId = dashboardItemId
        )
    )
}