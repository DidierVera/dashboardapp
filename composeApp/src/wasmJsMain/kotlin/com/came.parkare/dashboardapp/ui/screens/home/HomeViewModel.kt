package com.came.parkare.dashboardapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import kotlinx.browser.window

class HomeViewModel(
    private val preferences: WasmSharedPreferencesProvider
): ViewModel() {
    init {
        val ownIpAddress = "192.168.209.52"//window.location.hostname
        preferences.put(SELECTED_IP_ADDRESS, ownIpAddress)
    }
}