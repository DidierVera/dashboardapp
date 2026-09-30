package com.came.parkare.dashboardapp.ui.screens.home.properties

import com.came.parkare.dashboardapp.ui.screens.home.utils.EditingLocation

data class PropertiesState(

    val elementJsonCode: String = "",
    val location: EditingLocation? = null,
    val showTab: Boolean = false,

)
