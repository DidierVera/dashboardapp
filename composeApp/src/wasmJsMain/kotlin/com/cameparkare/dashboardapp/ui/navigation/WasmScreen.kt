package com.cameparkare.dashboardapp.ui.navigation

sealed class WasmScreen {
    object Home : WasmScreen()
    object Settings: WasmScreen()
}