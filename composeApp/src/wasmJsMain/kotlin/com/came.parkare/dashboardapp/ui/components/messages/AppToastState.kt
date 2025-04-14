package com.came.parkare.dashboardapp.ui.components.messages

data class AppToastState(
    val showMessage: Boolean = false,
    val timer: Float = 0F,
    val message: String = ""
)
