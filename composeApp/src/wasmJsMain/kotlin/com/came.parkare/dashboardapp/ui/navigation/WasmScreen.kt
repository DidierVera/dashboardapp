package com.came.parkare.dashboardapp.ui.navigation

sealed class WasmScreen {
    object Home : WasmScreen()
    object Settings: WasmScreen()
    object InitModal: WasmScreen()
}