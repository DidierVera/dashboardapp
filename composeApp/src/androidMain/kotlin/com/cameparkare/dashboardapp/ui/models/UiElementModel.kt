package com.cameparkare.dashboardapp.ui.models

sealed class UiElementModel{
    data class BoxDto(val data: BoxDataDto) : UiElementModel()
    data class SpacerDto(val data: SpacerDataDto) : UiElementModel()
    data class ColumnDto(val data: ColumnDataDto) : UiElementModel()
    data class TextDto(val data: TextDataDto) : UiElementModel()
    data class RowDto(val data: RowDataDto) : UiElementModel()
    data class ImageDto(val data: ImageDataDto) : UiElementModel()
}


data class BoxDataDto(
    val backgroundColor: String,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val width: Int? = null,
    val height: Int? = null,
    val padding: Int,
    val content: List<UiElementModel>
)

data class SpacerDataDto(
    val value: Int
)

data class ColumnDataDto(
    val backgroundColor: String,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val spacing: Int,
    val padding: Int,
    val content: List<UiElementModel>
)

data class TextDataDto(
    val dashboardItemId: String,
    val defaultText: String,
    val textSize: Int,
    val textColor: String,
    val padding: Int,
    val fontWeight: String,
    val dataKey: String? = null,
    val translations: Map<String, String>? = null
)

data class ImageDataDto(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val fileName: String? = null,
    val localFilePath: String? = null,
    val height: Int,
    val width: Int
)

data class RowDataDto(
    val backgroundColor: String,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val spacing: Int,
    val padding: Int,
    val content: List<UiElementModel>
)