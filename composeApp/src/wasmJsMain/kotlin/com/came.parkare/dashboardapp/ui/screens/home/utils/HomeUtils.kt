package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.StringResource

interface HomeUtils {
    val isShowingProperties: StateFlow<Boolean>
    val isShowingElements: StateFlow<Boolean>

    fun showElements(value: Boolean)
    fun showProperties(value: Boolean)
}