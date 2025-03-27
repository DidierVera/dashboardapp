package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.toDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.toModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@Serializable
data class ScreenDto(
    @SerialName("dispatch-code") val dispatchCode: Long,
    @SerialName("screen-id") val screenId: String,
    @SerialName("margin-top") val marginTop: Int = 20,
    @SerialName("margin-bottom") val marginBottom: Int = 20,
    @SerialName("margin-left") val marginLeft: Int = 20,
    @SerialName("margin-right") val marginRight: Int = 20,
    val data: List<ElementDto>
)

fun ScreenDto.toModel(): ScreenModel {
    return ScreenModel(
        screenId = screenId,
        dispatcherCode = dispatchCode,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight,
        elements = getElementsDtoToModel(data)
    )
}

fun ScreenModel.toDto(): ScreenDto {
    return ScreenDto(
        screenId = screenId,
        dispatchCode = dispatcherCode,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight,
        data = getElementsModelToDto(elements)
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

fun getElementsModelToDto(data: List<ElementModel>): List<ElementDto> {
    val result = mutableListOf<ElementDto>()
    for (element in data){
        when(element){
            is ElementModel.BoxModel -> result.add(element.toDto())
            is ElementModel.ColumnModel -> result.add(element.toDto())
            is ElementModel.ImageModel -> result.add(element.toDto())
            is ElementModel.RowModel -> result.add(element.toDto())
            is ElementModel.SpacerModel -> result.add(element.toDto())
            is ElementModel.TextModel -> result.add(element.toDto())
            is ElementModel.VideoModel -> result.add(element.toDto())
        }
    }
    return result
}