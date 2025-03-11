package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.toModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@Serializable
data class ScreenDto(
    @SerialName("dispatch-code") val dispatchCode: Long,
    @SerialName("screen-id") val screenId: String,
    val data: List<ElementDto>
)

fun ScreenDto.toModel(): ScreenModel {
    return ScreenModel(
        screenId = screenId, dispatcherCode = dispatchCode, elements = getElementsDtoToModel(data)
    )
}

fun getElementsDtoToModel(data: List<ElementDto>): List<ElementModel> {
    val result = mutableListOf<ElementModel>()
    for (element in data){
        when(element){
            is ElementDto.BoxDto -> result.add(element.data.toModel())
            is ElementDto.ColumnDto -> result.add(element.data.toModel())
            is ElementDto.ImageDto -> result.add(element.data.toModel())
            is ElementDto.RowDto -> result.add(element.data.toModel())
            is ElementDto.SpacerDto -> result.add(element.data.toModel())
            is ElementDto.TextDto -> result.add(element.data.toModel())
            is ElementDto.VideoDto -> result.add(element.data.toModel())
        }
    }
    return result
}