package com.cameparkare.dashboardapp.infrastructure.source.local.entities.components

import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.domain.models.components.ColumnDataModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.mapElementsEntityToModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.mapElementsModelToEntity


data class ColumnDataEntity(
    val style: CommonStyleEntity,
    val spacing: Int,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    @TypeConverters(ElementListConverter::class)
    val content: List<ElementEntity>
)

fun ElementModel.ColumnModel.toEntity(): ElementEntity.ColumnEntity {
    return ElementEntity.ColumnEntity(
        data = ColumnDataEntity(
            validValue = data.validValue,
            dataKey = data.dataKey,
            ditTypeCode = data.ditTypeCode,
            style = data.style.toEntity(),
            spacing = data.spacing,
            content = mapElementsModelToEntity(data.content)
        )
    )
}

fun ColumnDataEntity.toModel(): ElementModel.ColumnModel {
    return ElementModel.ColumnModel(
        data = ColumnDataModel(
            validValue = validValue,
            dataKey = dataKey,
            ditTypeCode = ditTypeCode,
            style = style.toModel(),
            spacing = spacing,
            content = mapElementsEntityToModel(content)
        )
    )
}