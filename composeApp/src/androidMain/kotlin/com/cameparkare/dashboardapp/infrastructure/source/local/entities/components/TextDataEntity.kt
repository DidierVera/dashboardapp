package com.cameparkare.dashboardapp.infrastructure.source.local.entities.components

import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.TextDataModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.MapConverter

data class TextDataEntity(
    val dashboardItemId: String,
    val defaultText: String,
    val textSize: Int,
    val textColor: String,
    val style: CommonStyleEntity,
    val fontWeight: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    @TypeConverters(MapConverter::class)
    val translations: Map<String, String>? = null
)

fun ElementModel.TextModel.toEntity(): ElementEntity.TextEntity {
    return ElementEntity.TextEntity(
        data = TextDataEntity(
            validValue = data.validValue,
            dataKey = data.dataKey,
            ditTypeCode = data.ditTypeCode,
            style = data.style.toEntity(),
            translations = data.translations,
            dashboardItemId = data.dashboardItemId,
            fontWeight = data.fontWeight,
            textColor = data.textColor,
            defaultText = data.defaultText,
            textSize = data.textSize
        )
    )
}

fun TextDataEntity.toModel(): ElementModel.TextModel {
    return ElementModel.TextModel(
        data = TextDataModel(
            validValue = validValue,
            dataKey = dataKey,
            ditTypeCode = ditTypeCode,
            style = style.toModel(),
            translations = translations,
            dashboardItemId = dashboardItemId,
            fontWeight = fontWeight,
            textColor = textColor,
            defaultText = defaultText,
            textSize = textSize
        )
    )
}