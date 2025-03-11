package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.SpacerDataModel
import kotlinx.serialization.Serializable

@Serializable
data class SpacerDataDto(
    val value: Int
)

fun SpacerDataDto.toModel(): ElementModel.SpacerModel {
    return ElementModel.SpacerModel(
        data = SpacerDataModel(
            value = value,
            style = CommonStyleModel()
        )
    )
}