package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements

import com.cameparkare.dashboardapp.domain.models.components.BoxDataModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.TextDataModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.getElementsDtoToModel
import kotlinx.serialization.Serializable


@Serializable
data class TextDataDto(
    val dashboardItemId: String,
    val defaultText: String,
    val textSize: Int,
    val textColor: String,
    val padding: Int,
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