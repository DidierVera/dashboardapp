package com.came.parkare.dashboardapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.browser.window

class HomeViewModel(
    private val preferences: SharedPreferencesProvider,
    private val wasmUtils: WasmUtilsHandler
): ViewModel() {

    fun showRequestLogin(message: String, onAccept:() -> Unit) {
        val ownIpAddress = "192.168.209.6"//window.location.hostname
        preferences.put(SELECTED_IP_ADDRESS, ownIpAddress)
        wasmUtils.showDialogRequestPassword(AppDialogState(
            requirePassword = true,
            onAccept = onAccept,
            message = message
        ))
    }
}