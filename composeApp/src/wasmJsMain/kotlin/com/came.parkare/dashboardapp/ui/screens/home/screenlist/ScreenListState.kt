package com.came.parkare.dashboardapp.ui.screens.home.screenlist

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

data class ScreenListState(
    val showTab: Boolean = false,
    val defaultScreens: List<ScreenDto> = emptyList()
)
