package com.came.parkare.dashboardapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
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
import kotlinx.coroutines.launch

class HomeViewModel(
    private val preferences: SharedPreferencesProvider,
    private val wasmUtils: WasmUtilsHandler,
    private val getConnectionConfig: GetConnectionConfig,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val validator: ErrorValidator,
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
        eventTabListener()
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch {
            loadConfigImages()
        }
    }

    private fun eventTabListener() {
        homeUtils.isShowingElements.onEach { display ->
            _state.update { it.copy(displayDefaultElements = display) }
        }.launchIn(viewModelScope)
        homeUtils.isShowingProperties.onEach { display ->
            _state.update { it.copy(displayProperties = display) }
        }.launchIn(viewModelScope)
        homeUtils.blankElements.onEach { display ->
            _state.update { it.copy(displayBlankElements = display) }
        }.launchIn(viewModelScope)
        homeUtils.defaultScreens.onEach { display ->
            _state.update { it.copy(displayDefaultScreens = display) }
        }.launchIn(viewModelScope)
    }

    fun displayProperties(){
        homeUtils.showProperties(!_state.value.displayProperties)
    }

    fun displayElements(){
        homeUtils.showElements(!_state.value.displayDefaultElements)
    }

    fun displayDefaultScreens(){
        homeUtils.showDefaultScreens(!_state.value.displayDefaultScreens)
    }

    fun displayBlankElement(){
        homeUtils.showBlankElements(!_state.value.displayBlankElements)
    }

    fun addDefaultScreen() {

    }

    fun addBlankScreen() {

    }

    fun importConfig() {

    }

    fun saveConfig() {

    }

    fun onCloseEditor(){
        homeUtils.hideAllTabs()
    }


    private suspend fun loadConfigImages() {
        wasmUtilsHandler.showLoading(true)
        when(val config = getConnectionConfig.invoke()){
            is ServiceResult.Error -> {
                validator.validate(config.error)
                wasmUtilsHandler.showLoading(false)
            }
            is ServiceResult.Success -> {
                homeUtils.setImagesSource(config.data?.files?.map { dto -> dto.toModel() }.orEmpty())
                homeUtils.setTextSizeScale(config.data?.textSizeScale ?: 10)
                wasmUtilsHandler.showLoading(false)
            }
        }
    }
}