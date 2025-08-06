package com.came.parkare.dashboardapp.ui.screens.home.screenlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScreenListViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val getScreensConfig: GetScreensConfig,
    private val validator: ErrorValidator,
    private val homeUtils: HomeUtils
):ViewModel() {

    private val _state = MutableStateFlow(ScreenListState())
    val state: StateFlow<ScreenListState>
        get() = _state.asStateFlow()

    fun initConfig(){
        viewModelScope.launch {
            //clearForm()
            //loadConfigImages()
            getCurrentScreenConfig()
        }
    }

    init {
        homeUtils.defaultScreens.onEach { display ->
            _state.update { it.copy(showTab = display) }
        }.launchIn(viewModelScope)
    }

    private suspend fun getCurrentScreenConfig() {
        wasmUtilsHandler.showLoading(true)
        when(val result = getScreensConfig.invoke()){
            is ServiceResult.Error -> {
                validator.validate(error = result.error)
                wasmUtilsHandler.showLoading(false)
            }
            is ServiceResult.Success -> {
                wasmUtilsHandler.showLoading(false)
                _state.update { it.copy(defaultScreens = result.data.orEmpty()) }
            }
        }
    }
}