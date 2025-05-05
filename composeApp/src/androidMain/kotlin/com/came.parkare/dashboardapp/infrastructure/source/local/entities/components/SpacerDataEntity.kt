package com.came.parkare.dashboardapp.infrastructure.source.local.entities.components

import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.components.SpacerDataModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import kotlinx.serialization.Serializable


@Serializable
data class SpacerDataEntity(
    val style: CommonStyleEntity,
    val value: Int
)

fun ElementModel.SpacerModel.toEntity(): ElementEntity.SpacerEntity{
    return ElementEntity.SpacerEntity(
        data = SpacerDataEntity(
            style = data.style.toEntity(),
            value = data.value
        )
    )
}

fun SpacerDataEntity.toModel(): ElementModel.SpacerModel {
    return ElementModel.SpacerModel(
        data = SpacerDataModel(
            style = style.toModel(),
            value = value
        )
    )
}