package com.cameparkare.dashboardapp.domain.models.Components

sealed class ElementModel{
    data class BoxModel(val data: BoxDataModel) : ElementModel()
    data class SpacerModel(val data: SpacerDataModel) : ElementModel()
    data class ColumnModel(val data: ColumnDataModel) : ElementModel()
    data class TextModel(val data: TextDataModel) : ElementModel()
    data class RowModel(val data: RowDataModel) : ElementModel()
    data class ImageModel(val data: ImageDataModel) : ElementModel()
    data class VideoModel(val data: VideoDataModel) : ElementModel()
}