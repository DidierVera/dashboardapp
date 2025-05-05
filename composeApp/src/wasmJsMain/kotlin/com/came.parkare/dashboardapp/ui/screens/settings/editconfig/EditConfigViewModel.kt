package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.BoxDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ColumnDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ImageDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.RowDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.SpacerDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.TextDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.VideoDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.toDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.screen_config_saved_message
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
    private val saveScreenConfig: SaveScreenConfig,
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
                    _state.update { it.copy(screens = result.data.orEmpty()) }
                }
            }
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

    fun selectItemOnScreen(element: ElementModel, position: Int, screen: ScreenModel) {
        when(element){
            is ElementModel.BoxModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
            is ElementModel.ColumnModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
            is ElementModel.ImageModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
            is ElementModel.RowModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
            is ElementModel.SpacerModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
            is ElementModel.TextModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
            is ElementModel.VideoModel -> {
                encodeAndSetEditableElement(element.toDto())
                setElementAndPosition(element.toDto(), position, screen)
            }
        }
    }

    private fun setElementAndPosition(elementDto: ElementDto, position: Int, screen: ScreenModel){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedElement = elementDto,
                    elementPosition = position,
                    containerScreen = screen.toDto()
                )
            }
        }
    }

    fun saveChanges(){
        viewModelScope.launch {
            try {
                wasmUtilsHandler.showLoading(true)
                val newData = _state.value.screens
                saveScreenConfig.invoke(newData)
                wasmUtilsHandler.showLoading(false)
                wasmUtilsHandler.showToastMessage (Res.string.screen_config_saved_message)
            }catch (e: Exception){
                e.printStackTrace()
                wasmUtilsHandler.showLoading(false)
                wasmUtilsHandler.showToastMessage ("Failed to save changes: ${e.message}")
            }
        }
    }
    fun applyChanges() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)

            try {
                val state = _state.value
                val selectedElement = state.selectedElement
                val selectedScreen = state.containerScreen ?: run {
                    wasmUtilsHandler.showLoading(false)
                    return@launch
                }

                val currentScreenElements = selectedScreen.data.toMutableList()
                val elementPosition = state.elementPosition ?: run {
                    wasmUtilsHandler.showLoading(false)
                    return@launch
                }

                if (selectedElement != null && elementPosition in currentScreenElements.indices) {
                    val newElement = when (selectedElement) {
                        is ElementDto.BoxDto -> getElementFromJson<ElementDto.BoxDto>()
                        is ElementDto.ColumnDto -> getElementFromJson<ElementDto.ColumnDto>()
                        is ElementDto.ImageDto -> getElementFromJson<ElementDto.ImageDto>()
                        is ElementDto.RowDto -> getElementFromJson<ElementDto.RowDto>()
                        is ElementDto.SpacerDto -> getElementFromJson<ElementDto.SpacerDto>()
                        is ElementDto.TextDto -> getElementFromJson<ElementDto.TextDto>()
                        is ElementDto.VideoDto -> getElementFromJson<ElementDto.VideoDto>()
                    }

                    currentScreenElements[elementPosition] = newElement

                    val newScreen = selectedScreen.copy(
                        data = currentScreenElements.toList()
                    )

                    val newListOfScreens = state.screens.toMutableList().apply {
                        val screenIndex = indexOfFirst { it.screenId == selectedScreen.screenId }
                        if (screenIndex != -1) {
                            this[screenIndex] = newScreen
                        }
                    }

                    _state.update { it.copy(screens = newListOfScreens) }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error appropriately (log or show to user)
                wasmUtilsHandler.showToastMessage ("Failed to apply changes: ${e.message}")
            } finally {
                wasmUtilsHandler.showLoading(false)
            }
        }
    }

    private inline fun <reified T> getElementFromJson(): T {
        val mJson = _state.value.contentFile
        return try {
            Json.decodeFromString(mJson)
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Failed to parse JSON for ${T::class.simpleName}", e)
        }
    }
}