package com.came.parkare.dashboardapp.ui.screens.home.utils

import kotlinx.coroutines.flow.StateFlow

interface HomeUtils {
    val isShowingProperties: StateFlow<Boolean>
    val isShowingElements: StateFlow<Boolean>
    val blankElements: StateFlow<Boolean>
    val defaultScreens: StateFlow<Boolean>
    val displayControls: StateFlow<Boolean>

    fun showElements(value: Boolean)
    fun showProperties(value: Boolean)
    fun showBlankElements(value: Boolean)
    fun showDefaultScreens(value: Boolean)
    fun hideAllTabs()
}