package com.came.parkare.dashboardapp.ui.screens.settings.testing

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

data class TestingState(
    val screens: List<ScreenDto> = emptyList(),
    val selectedScreens: List<ScreenDto> = emptyList(),
)
