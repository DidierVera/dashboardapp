package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

sealed class ElementDto{
    data class BoxDto(val data: BoxDataDto) : ElementDto()
    data class SpacerDto(val data: SpacerDataDto) : ElementDto()
    data class ColumnDto(val data: ColumnDataDto) : ElementDto()
    data class TextDto(val data: TextDataDto) : ElementDto()
    data class RowDto(val data: RowDataDto) : ElementDto()
    data class ImageDto(val data: ImageDataDto) : ElementDto()
}


data class BoxDataDto(
    val backgroundColor: String,
    val density: Int,
    val roundBorder: Int,
    val hasShadow: Boolean,
    val width: Int? = null,
    val height: Int? = null,
    val padding: Int,
    val content: List<ElementDto>
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
    val content: List<ElementDto>
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
    val content: List<ElementDto>
)