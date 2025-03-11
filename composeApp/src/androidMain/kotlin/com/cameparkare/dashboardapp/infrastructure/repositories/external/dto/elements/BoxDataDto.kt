package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.BoxDataModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ElementDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ElementSerializer
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.getElementsDtoToModel
import kotlinx.serialization.Serializable


@Serializable
data class BoxDataDto(
    val backgroundColor: String,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val margin: Int? = null,
    val padding: Int,
    val content: List<@Serializable(with = ElementSerializer::class) ElementDto>
)

fun BoxDataDto.toModel(): ElementModel.BoxModel{
    return ElementModel.BoxModel(
        data = BoxDataModel(
            dataKey = dataKey, validValue = validValue, ditTypeCode = ditTypeCode,
            style = CommonStyleModel(
                backgroundColor = backgroundColor, density = density, roundBorder = roundBorder,
                hasShadow = hasShadow, width = width, height = height,
                margin = margin, padding = padding
            ),
            content = getElementsDtoToModel(content)
        )
    )
}