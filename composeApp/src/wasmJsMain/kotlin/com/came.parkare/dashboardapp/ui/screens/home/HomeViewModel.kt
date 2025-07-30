package com.came.parkare.dashboardapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val preferences: SharedPreferencesProvider,
    private val wasmUtils: WasmUtilsHandler,
    private val homeUtils: HomeUtils
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    fun showRequestLogin(message: String, onAccept:() -> Unit) {
        val ownIpAddress = "192.168.209.6"//window.location.hostname
        preferences.put(SELECTED_IP_ADDRESS, ownIpAddress)
        wasmUtils.showDialogRequestPassword(AppDialogState(
            requirePassword = true,
            onAccept = onAccept,
            message = message
        ))
    }

    init {
        homeUtils.isShowingElements.onEach {
            showElements(it)
        }.launchIn(viewModelScope)
        homeUtils.isShowingProperties.onEach {
            showProperties(it)
        }.launchIn(viewModelScope)
    }

    private fun showProperties(newValue: Boolean){
        _state.update { it.copy(isShowingProperties = newValue, isShowingElements = false) }
    }

    private fun showElements(newValue: Boolean){
        _state.update { it.copy(isShowingElements = newValue, isShowingProperties = false) }
    }
}