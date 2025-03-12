package com.cameparkare.dashboardapp.infrastructure.source.local.entities.components

import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel

data class CommonStyleEntity(
    val backgroundColor: String? = null,
    val density: Int = 100,
    val roundBorder: Int = 0,
    val hasShadow: Boolean = false,
    val padding: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val margin: Int? = null
)

fun CommonStyleModel.toEntity(): CommonStyleEntity {
    return CommonStyleEntity(
        backgroundColor = backgroundColor,
        density = density,
        roundBorder = roundBorder,
        hasShadow = hasShadow,
        padding = padding,
        width = width,
        height = height,
        margin = margin
    )
}

fun CommonStyleEntity.toModel(): CommonStyleModel {
    return CommonStyleModel(
        backgroundColor = backgroundColor,
        density = density,
        roundBorder = roundBorder,
        hasShadow = hasShadow,
        padding = padding,
        width = width,
        height = height,
        margin = margin
    )
}