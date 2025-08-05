package com.came.parkare.dashboardapp.ui.screens.home.properties

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto

data class PropertiesState(

    val element: ElementDto? = null,
    val showTab: Boolean = false,

)
