package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfigEditorViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val validator: ErrorValidator,
    private val getScreensConfig: GetScreensConfig,
    private val resourceUtils: ResourceUtils

): ViewModel() {

    private val _state = MutableStateFlow(ConfigEditorUiState())
    val state: StateFlow<ConfigEditorUiState>
        get() = _state.asStateFlow()


    fun initConfig(){
        viewModelScope.launch {
            clearForm()
            getCurrentScreenConfig()
        }
    }

    init {
        resourceUtils.imagesSource.onEach { images ->
            _state.update { it.copy(imagesSource = images) }
        }.launchIn(viewModelScope)

        resourceUtils.textSizeScale.onEach { size ->
            _state.update { it.copy(textSizeScale = size) }
        }.launchIn(viewModelScope)

        resourceUtils.editableTemplate.onEach { template ->
            _state.update { it.copy(editingTemplate = template) }
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
                //_state.update { it.copy(screens = result.data.orEmpty()) }
            }
        }
    }
    private fun clearForm(){
        _state.update { it.copy(
            imagesSource = emptyList(),
            elementsByScreen = emptyList()
        ) }
    }

}