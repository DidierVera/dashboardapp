package com.came.parkare.dashboardapp.ui.screens.home.elementlist

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto

data class ElementListState(
    val defaultItems: List<ElementDto> = emptyList(),
    val blankElements: List<ElementDto> = emptyList(),
    val showBlankTab: Boolean = false,
    val showDefaultTab: Boolean = false
)
