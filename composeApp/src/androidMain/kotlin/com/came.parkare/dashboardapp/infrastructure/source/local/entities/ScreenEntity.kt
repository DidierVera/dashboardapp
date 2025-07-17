package com.came.parkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.components.toEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.components.toModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = SCREEN_TABLE_NAME)
data class ScreenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val screenId: String,
    val dispatcherCode: Long,
    val marginTop: Int = 0,
    val marginBottom: Int = 0,
    val marginLeft: Int = 0,
    val marginRight: Int = 0,
    @TypeConverters(ElementListConverter::class)
    val elements: List<ElementEntity>
)

fun ScreenModel.toEntity(): ScreenEntity {
    return ScreenEntity(
        screenId = screenId,
        dispatcherCode = dispatcherCode,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight,
        elements = mapElementsModelToEntity(elements)
    )
}

fun ScreenEntity.toModel(): ScreenModel {
    return ScreenModel(
        screenId = screenId,
        dispatcherCode = dispatcherCode,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight,
        elements = mapElementsEntityToModel(elements)
    )
}

fun mapElementsEntityToModel(elements: List<ElementEntity>): List<ElementModel> {
    val result = mutableListOf<ElementModel>()
    for (element in elements){
        when(element){
            is ElementEntity.BoxEntity -> result.add(element.data.toModel())
            is ElementEntity.ColumnEntity -> result.add(element.data.toModel())
            is ElementEntity.ImageEntity -> result.add(element.data.toModel())
            is ElementEntity.RowEntity -> result.add(element.data.toModel())
            is ElementEntity.SpacerEntity -> result.add(element.data.toModel())
            is ElementEntity.TextEntity -> result.add(element.data.toModel())
            is ElementEntity.VideoEntity -> result.add(element.data.toModel())
        }
    }
    return result
}

fun mapElementsModelToEntity(data: List<ElementModel>): List<ElementEntity> {
    val result = mutableListOf<ElementEntity>()
    for (element in data){
        when(element){
            is ElementModel.BoxModel -> result.add(element.toEntity())
            is ElementModel.ColumnModel -> result.add(element.toEntity())
            is ElementModel.ImageModel -> result.add(element.toEntity())
            is ElementModel.RowModel -> result.add(element.toEntity())
            is ElementModel.SpacerModel -> result.add(element.toEntity())
            is ElementModel.TextModel -> result.add(element.toEntity())
            is ElementModel.VideoModel -> result.add(element.toEntity())
        }
    }
    return result
}