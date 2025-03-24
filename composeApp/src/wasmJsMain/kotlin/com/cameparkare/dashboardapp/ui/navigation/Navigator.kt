package com.cameparkare.dashboardapp.ui.navigation

import androidx.compose.runtime.mutableStateListOf

class Navigator {
    private val _screens = mutableStateListOf<WasmScreen>()
    val screens: List<WasmScreen> = _screens

    fun navigateTo(screen: WasmScreen) {
        _screens.add(screen)
    }

    fun goBack() {
        if (_screens.size > 1) {
            _screens.removeLast()
        }
    }

    fun resetTo(screen: WasmScreen) {
        _screens.clear()
        _screens.add(screen)
    }
}