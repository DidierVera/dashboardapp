package com.cameparkare.dashboardapp.ui.navigation

sealed class WasmScreen {
    object Home : WasmScreen()
    object Settings: WasmScreen()
    object MainScreen: WasmScreen()
    data class Details(val id: String) : WasmScreen()
}