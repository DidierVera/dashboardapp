package com.came.parkare.dashboardapp.ui.components.messages

import org.jetbrains.compose.resources.StringResource

data class AppToastState(
    val showMessage: Boolean = false,
    val timer: Float = 0F,
    val message: String = "",
    val messageRes: StringResource? = null
)
