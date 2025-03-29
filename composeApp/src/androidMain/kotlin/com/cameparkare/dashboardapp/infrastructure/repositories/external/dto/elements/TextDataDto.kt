package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.BoxDataModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.TextDataModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ElementDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.getElementsDtoToModel
import kotlinx.serialization.Serializable


@Serializable
data class TextDataDto(
    val dashboardItemId: String,
    val defaultText: String,
    val textSize: Int,
    val textColor: String,
    val padding: Int? = 0,
    val fontWeight: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val translations: Map<String, String>? = null
)

fun TextDataDto.toModel(): ElementModel.TextModel{
    return ElementModel.TextModel(
        data = TextDataModel(
            dataKey = dataKey, validValue = validValue, ditTypeCode = ditTypeCode,
            fontWeight = fontWeight,
            textColor = textColor,
            translations = translations,
            dashboardItemId = dashboardItemId,
            defaultText = defaultText,
            textSize = textSize,
            style = CommonStyleModel(
                 padding = padding
            )
        )
    )
}
fun ElementModel.TextModel.toDto(): ElementDto.TextDto{
    return ElementDto.TextDto(
        elementType = "text",
        data = TextDataDto(
            dataKey = data.dataKey, validValue = data.validValue, ditTypeCode = data.ditTypeCode,
            fontWeight = data.fontWeight,
            textColor = data.textColor,
            translations = data.translations,
            dashboardItemId = data.dashboardItemId,
            defaultText = data.defaultText,
            textSize = data.textSize,
                 padding = data.style.padding

        )
    )
}