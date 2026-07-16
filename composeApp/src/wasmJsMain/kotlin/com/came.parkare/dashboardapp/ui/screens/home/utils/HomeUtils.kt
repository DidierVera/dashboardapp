package com.came.parkare.dashboardapp.ui.screens.home.utils

import kotlinx.coroutines.flow.StateFlow

interface HomeUtils {
    val isShowingProperties: StateFlow<Boolean>
    val isShowingDefaultElements: StateFlow<Boolean>
    val isShowingBlankElements: StateFlow<Boolean>
    val isShowingDefaultScreens: StateFlow<Boolean>
    val blankScreen: StateFlow<Boolean>
    val displayControls: StateFlow<Boolean>

    fun showElements(value: Boolean)
    fun showProperties(value: Boolean)
    fun showBlankElements(value: Boolean)
    fun showBlankScreen(value: Boolean)
    fun showDefaultScreens(value: Boolean)
    fun hideAllTabs()
}