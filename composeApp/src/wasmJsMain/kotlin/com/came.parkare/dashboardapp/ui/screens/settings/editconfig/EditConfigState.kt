package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto

data class EditConfigState(
    val contentFile: String = "",
    val screens: List<ScreenDto> = emptyList(),
    val selectedElement: ElementDto? = null,
    val elementPosition: Int? = null,
    val containerScreen: ScreenDto? = null
)
