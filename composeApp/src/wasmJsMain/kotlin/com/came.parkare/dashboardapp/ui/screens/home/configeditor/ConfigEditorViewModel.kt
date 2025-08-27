package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.toDto
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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class ConfigEditorViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val validator: ErrorValidator,
    private val getScreensConfig: GetScreensConfig,
    private val resourceUtils: ResourceUtils,
    private val homeUtils: HomeUtils

): ViewModel() {

    private val _state = MutableStateFlow(ConfigEditorUiState())
    val state: StateFlow<ConfigEditorUiState>
        get() = _state.asStateFlow()


    fun initConfig(){
        viewModelScope.launch {
            clearForm()
            getCurrentScreenConfig()
            initEditor()
        }
    }

    private fun initEditor() {
        resourceUtils.imagesSource.onEach { images ->
            _state.update { it.copy(imagesSource = images) }
        }.launchIn(viewModelScope)

        resourceUtils.textSizeScale.onEach { size ->
            _state.update { it.copy(textSizeScale = size) }
        }.launchIn(viewModelScope)

        resourceUtils.editableTemplate.onEach { template ->
            _state.update { it.copy(editingTemplate = template) }
            val firstScreen = template.screens.first()
            _state.update { it.copy(elementsByScreen = firstScreen.elements) }
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

    fun selectItemOnScreen(element: ElementModel, position: Int) {
        when(element){
            is ElementModel.BoxModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
            is ElementModel.ColumnModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
            is ElementModel.ImageModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
            is ElementModel.RowModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
            is ElementModel.SpacerModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
            is ElementModel.TextModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
            is ElementModel.VideoModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position)
            }
        }
    }

    private fun setElementAndPosition(elementDto: ElementDto, position: Int){
        viewModelScope.launch {
            resourceUtils.setEditingElement(Json.encodeToString(elementDto))
            homeUtils.showProperties(true)
//            _state.update {
//                it.copy(
//                    selectedElement = elementDto,
//                    elementPosition = position
//                )
//            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private inline fun<reified T> encodeAndSetEditableElement(element: T) {
        viewModelScope.launch {
            val prettyJson = Json {
                prettyPrint = true
                prettyPrintIndent = " "
                encodeDefaults = true
            }
            val fileContent = prettyJson.encodeToString(element)
//            _state.update {
//                it.copy( elementJsonCode = fileContent )
//            }
        }
    }


    fun selectScreen(screen: ScreenModel){
        if (_state.value.screenViewer == screen.screenId){
            _state.update { it.copy(screenViewer = null, elementsByScreen = emptyList()) }
        }else{
            _state.update { it.copy(screenViewer = screen.screenId, elementsByScreen = screen.elements)}
        }
    }
}