package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

data class EditConfigState(
    val contentFile: String = "",
    val screens: List<ScreenDto> = emptyList(),
    val selectedScreen: ScreenDto? = null
)
