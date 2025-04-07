package com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements

import com.came.parkare.dashboardapp.domain.models.components.CommonStyleModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.components.RowDataModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.getElementsDtoToModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.getElementsModelToDto
import kotlinx.serialization.Serializable


@Serializable
data class RowDataDto(
    val backgroundColor: String?,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val spacing: Int,
    val padding: Int? = 0,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val content: List<@Serializable(with = ElementSerializer::class) ElementDto>
)

fun RowDataDto.toModel(): ElementModel.RowModel{
    return ElementModel.RowModel(
        data = RowDataModel(
            dataKey = dataKey, validValue = validValue, ditTypeCode = ditTypeCode, spacing = spacing,
            style = CommonStyleModel(
                backgroundColor = backgroundColor, density = density, roundBorder = roundBorder,
                hasShadow = hasShadow, padding = padding
            ),
            content = getElementsDtoToModel(content)
        )
    )
}

fun ElementModel.RowModel.toDto(): ElementDto.RowDto {
    return ElementDto.RowDto (
        elementType = "row",
        data = RowDataDto(
            dataKey = data.dataKey, validValue = data.validValue, ditTypeCode = data.ditTypeCode,
            spacing = data.spacing, backgroundColor = data.style.backgroundColor,
            density = data.style.density, roundBorder = data.style.roundBorder,
            hasShadow = data.style.hasShadow, padding = data.style.padding,
            content = getElementsModelToDto(data.content)
        )
    )
}