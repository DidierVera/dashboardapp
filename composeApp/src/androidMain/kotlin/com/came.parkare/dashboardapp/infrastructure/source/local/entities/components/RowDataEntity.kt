package com.came.parkare.dashboardapp.infrastructure.source.local.entities.components

import androidx.room.TypeConverters
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.components.RowDataModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.mapElementsEntityToModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.mapElementsModelToEntity
import kotlinx.serialization.Serializable

@Serializable
data class RowDataEntity(
    val style: CommonStyleEntity,
    val spacing: Int,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    @TypeConverters(ElementListConverter::class)
    val content: List<ElementEntity>
)

fun ElementModel.RowModel.toEntity(): ElementEntity.RowEntity {
    return ElementEntity.RowEntity(
        data = RowDataEntity(
            validValue = data.validValue,
            dataKey = data.dataKey,
            ditTypeCode = data.ditTypeCode,
            style = data.style.toEntity(),
            spacing = data.spacing,
            content = mapElementsModelToEntity(data.content)
        )
    )
}

fun RowDataEntity.toModel(): ElementModel.RowModel {
    return ElementModel.RowModel(
        data = RowDataModel(
            validValue = validValue,
            dataKey = dataKey,
            ditTypeCode = ditTypeCode,
            style = style.toModel(),
            spacing = spacing,
            content = mapElementsEntityToModel(content)
        )
    )
}