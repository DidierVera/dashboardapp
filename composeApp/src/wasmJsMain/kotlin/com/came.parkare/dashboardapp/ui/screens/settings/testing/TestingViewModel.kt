package com.came.parkare.dashboardapp.ui.screens.settings.testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.orEmpty

class TestingViewModel(
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val getScreensConfig: GetScreensConfig,): ViewModel() {

    private val _state = MutableStateFlow(TestingState())
    val state: StateFlow<TestingState>
        get() = _state.asStateFlow()

    fun initConfig(){
        viewModelScope.launch {
            getCurrentScreenConfig()
        }
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
                _state.update { it.copy(screens = result.data.orEmpty()) }
            }
        }
    }
}