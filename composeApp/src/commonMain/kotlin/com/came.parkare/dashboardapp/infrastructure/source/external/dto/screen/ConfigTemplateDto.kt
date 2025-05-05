package com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import kotlinx.serialization.Serializable

@Serializable
data class ConfigTemplateDto(
    val id: Long = 0,
    val templateName: String,
    val screens: List<ScreenDto>
)

fun ConfigTemplateDto.toModel(): ConfigTemplateModel {
    return ConfigTemplateModel(
        id = id,
        templateName = templateName,
        screens = screens.map { it.toModel() }
    )
}