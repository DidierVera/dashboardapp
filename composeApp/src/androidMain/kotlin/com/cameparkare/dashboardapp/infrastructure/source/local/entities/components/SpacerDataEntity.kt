package com.cameparkare.dashboardapp.infrastructure.source.local.entities.components

import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.SpacerDataModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity


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