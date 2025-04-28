package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.toDto
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class EditConfigViewModel(
    private val getScreensConfig: GetScreensConfig,
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val appLogger: AppLogger
): ViewModel() {

    private val _state = MutableStateFlow(EditConfigState())
    val state: StateFlow<EditConfigState>
        get() = _state.asStateFlow()


    fun getCurrentScreenConfig() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            when(val result = getScreensConfig.invoke()){
                is ServiceResult.Error -> {
                    validator.validate(error = result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    wasmUtilsHandler.showLoading(false)
                    val firstScreen = result.data.orEmpty().first()
                    selectEditableScreen(firstScreen)
                    _state.update { it.copy(screens = result.data.orEmpty()) }
                }
            }
        }
    }

    private fun selectEditableScreen(screen: ScreenDto){
        viewModelScope.launch {
            encodeAndSetEditableElement(screen)
            _state.update { it.copy(selectedScreen = screen) }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private inline fun<reified T> encodeAndSetEditableElement(element: T) {
        viewModelScope.launch {
            val prettyJson = Json {
                prettyPrint = true
                prettyPrintIndent = " "
            }
            val fileContent = prettyJson.encodeToString(element)
            _state.update {
                it.copy( contentFile = fileContent )
            }
        }
    }

    fun setValues(fileContent: String){
        viewModelScope.launch {
            _state.update { it.copy(contentFile = fileContent) }
        }
    }

    fun selectScreen(screen: ScreenDto) {
        selectEditableScreen(screen)
    }

    fun selectItemOnScreen(mItem: ElementModel) {
        when(mItem){
            is ElementModel.BoxModel -> encodeAndSetEditableElement(mItem.toDto())
            is ElementModel.ColumnModel -> encodeAndSetEditableElement(mItem.toDto())
            is ElementModel.ImageModel -> encodeAndSetEditableElement(mItem.toDto())
            is ElementModel.RowModel -> encodeAndSetEditableElement(mItem.toDto())
            is ElementModel.SpacerModel -> encodeAndSetEditableElement(mItem.toDto())
            is ElementModel.TextModel -> encodeAndSetEditableElement(mItem.toDto())
            is ElementModel.VideoModel -> encodeAndSetEditableElement(mItem.toDto())
        }
    }
}