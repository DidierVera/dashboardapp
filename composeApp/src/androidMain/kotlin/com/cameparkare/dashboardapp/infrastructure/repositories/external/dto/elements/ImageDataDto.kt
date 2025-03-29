package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.BoxDataModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.ImageDataModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ElementDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.getElementsDtoToModel
import kotlinx.serialization.Serializable

@Serializable
data class ImageDataDto(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val fileName: String? = null,
    val localFilePath: String? = null,
    val intervalTime: Int? = null,
    val height: Int? = 60,
    val width: Int? = 60
)

fun ImageDataDto.toModel(): ElementModel.ImageModel{
    return ElementModel.ImageModel(
        data = ImageDataModel(
            dataKey = dataKey, validValue = validValue, ditTypeCode = ditTypeCode,
            style = CommonStyleModel(
                width = width, height = height
            ),
            dashboardItemId = dashboardItemId,
            folderPath = localFilePath,
            fileName = fileName,
            interval = intervalTime
        )
    )
}

fun ElementModel.ImageModel.toDto(): ElementDto.ImageDto {
    return ElementDto.ImageDto (
        elementType = "image",
        data = ImageDataDto(
            dataKey = data.dataKey, validValue = data.validValue, ditTypeCode = data.ditTypeCode,
                width = data.style.width, height = data.style.height,
            dashboardItemId = data.dashboardItemId,
            localFilePath = data.folderPath,
            fileName = data.fileName,
            intervalTime = data.interval
        )
    )
}