package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.ColumnDataModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ElementDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ElementSerializer
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.getElementsDtoToModel
import kotlinx.serialization.Serializable


@Serializable
data class ColumnDataDto(
    val backgroundColor: String,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val spacing: Int,
    val padding: Int,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val content: List<@Serializable(with = ElementSerializer::class) ElementDto>
)

fun ColumnDataDto.toModel(): ElementModel.ColumnModel{
    return ElementModel.ColumnModel(
        data = ColumnDataModel(
            dataKey = dataKey, validValue = validValue, ditTypeCode = ditTypeCode, spacing = spacing,
            style = CommonStyleModel(
                backgroundColor = backgroundColor, density = density, roundBorder = roundBorder,
                hasShadow = hasShadow, padding = padding
            ),
            content = getElementsDtoToModel(content)
        )
    )
}