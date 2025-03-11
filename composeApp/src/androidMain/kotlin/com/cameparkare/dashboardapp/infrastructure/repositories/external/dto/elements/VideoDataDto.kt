package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.ImageDataModel
import com.cameparkare.dashboardapp.domain.models.components.VideoDataModel
import kotlinx.serialization.Serializable


@Serializable
data class VideoDataDto(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val fileName: String? = null,
    val localFilePath: String? = null,
    val height: Int,
    val width: Int
)


fun VideoDataDto.toModel(): ElementModel.VideoModel{
    return ElementModel.VideoModel(
        data = VideoDataModel(
            dataKey = dataKey, validValue = validValue, ditTypeCode = ditTypeCode,
            style = CommonStyleModel(
                width = width, height = height
            ),
            dashboardItemId = dashboardItemId,
            folderPath = localFilePath,
            fileName = fileName
        )
    )
}