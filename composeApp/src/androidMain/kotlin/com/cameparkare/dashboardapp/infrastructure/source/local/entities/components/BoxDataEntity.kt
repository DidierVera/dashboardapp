package com.cameparkare.dashboardapp.infrastructure.source.local.entities.components

import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.domain.models.components.BoxDataModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.mapElementsModelToEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.mapElementsEntityToModel

data class BoxDataEntity(
    val style: CommonStyleEntity,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    @TypeConverters(ElementListConverter::class)
    val content: List<ElementEntity>
)

fun ElementModel.BoxModel.toEntity(): ElementEntity.BoxEntity {
    return ElementEntity.BoxEntity(
        data = BoxDataEntity(
            validValue = data.validValue,
            dataKey = data.dataKey,
            ditTypeCode = data.ditTypeCode,
            style = data.style.toEntity(),
            content = mapElementsModelToEntity(data.content)
        )
    )
}

fun BoxDataEntity.toModel(): ElementModel.BoxModel {
    return ElementModel.BoxModel(
        data = BoxDataModel(
            validValue = validValue,
            dataKey = dataKey,
            ditTypeCode = ditTypeCode,
            style = style.toModel(),
            content = mapElementsEntityToModel(content)
        )
    )
}