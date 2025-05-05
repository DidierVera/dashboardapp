package com.came.parkare.dashboardapp.ui.components.dialog

data class AppDialogState(
    val message: String = "",
    val onCancel: () -> Unit = {},
    val onAccept: () -> Unit = {},
    val requirePassword: Boolean = false
)
