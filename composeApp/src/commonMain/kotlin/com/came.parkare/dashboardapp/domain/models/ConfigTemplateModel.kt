package com.came.parkare.dashboardapp.domain.models

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ConfigTemplateDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto

data class ConfigTemplateModel(
    val id: Long = 0,
    val templateName: String,
    val screens: List<ScreenModel>
)

fun ConfigTemplateModel.toDto(): ConfigTemplateDto{
    return ConfigTemplateDto(
        id = id,
        templateName = templateName,
        screens = screens.map { it.toDto() }
    )
}