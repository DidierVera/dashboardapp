package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.StringResource

interface HomeUtils {
    val isShowingProperties: StateFlow<Boolean>
    val isShowingElements: StateFlow<Boolean>
    val blankElements: StateFlow<Boolean>
    val defaultScreens: StateFlow<Boolean>

    fun showElements(value: Boolean)
    fun showProperties(value: Boolean)
    fun showBlankElements(value: Boolean)
    fun showDefaultScreens(value: Boolean)
}