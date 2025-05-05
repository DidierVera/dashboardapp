package com.came.parkare.dashboardapp.infrastructure.source.local.entities.components

import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.components.ImageDataModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.mapElementsModelToEntity
import kotlinx.serialization.Serializable

@Serializable
data class ImageDataEntity(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val fileName: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val folderPath: String? = null,
    val interval: Int? = null,
    val style: CommonStyleEntity
)

fun ElementModel.ImageModel.toEntity(): ElementEntity.ImageEntity {
    return ElementEntity.ImageEntity(
        data = ImageDataEntity(
            validValue = data.validValue,
            dataKey = data.dataKey,
            ditTypeCode = data.ditTypeCode,
            style = data.style.toEntity(),
            folderPath = data.folderPath,
            interval = data.interval,
            fileName = data.fileName,
            dashboardItemId = data.dashboardItemId
        )
    )
}

fun ImageDataEntity.toModel(): ElementModel.ImageModel {
    return ElementModel.ImageModel(
        data = ImageDataModel(
            validValue = validValue,
            dataKey = dataKey,
            ditTypeCode = ditTypeCode,
            style = style.toModel(),
            folderPath = folderPath,
            interval = interval,
            fileName = fileName,
            dashboardItemId = dashboardItemId
        )
    )
}