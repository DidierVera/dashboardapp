package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.toDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.screen_config_saved_message
import kotlinx.browser.window
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
    private val getConnectionConfig: GetConnectionConfig,
    private val preferences: SharedPreferencesProvider,
    private val appLogger: AppLogger
): ViewModel() {

    private val _state = MutableStateFlow(EditConfigState())
    val state: StateFlow<EditConfigState>
        get() = _state.asStateFlow()

    fun initConfig(){
        viewModelScope.launch {
            clearForm()
            loadConfigImages()
            getCurrentScreenConfig()
        }
    }

    private suspend fun loadConfigImages() {
        wasmUtilsHandler.showLoading(true)
        when(val config = getConnectionConfig.invoke()){
            is ServiceResult.Error -> {
                validator.validate(config.error)
                wasmUtilsHandler.showLoading(false)
            }
            is ServiceResult.Success -> {
                _state.update { it.copy(
                    textSizeScale = config.data?.textSizeScale ?: 10,
                    imagesSource = config.data?.files?.map { dto -> dto.toModel() }.orEmpty()
                ) }
                wasmUtilsHandler.showLoading(false)
            }
        }
    }

    private fun clearForm(){
        _state.update { it.copy(
            imagesSource = emptyList(),
            screenViewer = null,
            screens = emptyList(),
            elementsByScreen = emptyList(),
            contentFile = ""
        ) }
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

    fun selectScreen(screen: ScreenModel){
        if (_state.value.screenViewer == screen.screenId){
            _state.update { it.copy(screenViewer = null, elementsByScreen = emptyList()) }
        }else{
            _state.update { it.copy(screenViewer = screen.screenId, elementsByScreen = screen.elements)}
        }
    }

    fun launchPasswordRequest(message: String, onClick: () -> Unit){
        wasmUtilsHandler.showDialogMessage(
            AppDialogState(
            message = message,
            onAccept = onClick, requirePassword = true
        ))
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
            _state.update {
                it.copy(
                    selectedElement = elementDto,
                    elementPosition = position

                )
            }
        }
    }

    fun saveChanges(){
        viewModelScope.launch {
            try {
                wasmUtilsHandler.showLoading(true)
                val newData = _state.value.screens
                val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
                saveScreenConfig.invoke(ipAddress, newData)
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
                val selectedScreen = state.screens.first { it.screenId == state.screenViewer }

                val currentScreenElements = selectedScreen.data.toMutableList()
                val elementPosition = state.elementPosition ?: run {
                    wasmUtilsHandler.showLoading(false)
                    return@launch
                }

                if (selectedElement != null && elementPosition in currentScreenElements.indices) {

                    val newElement = Json.decodeFromString<ElementDto>(state.contentFile)
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

                    _state.update { it.copy(elementsByScreen = newScreen.toModel().elements) }
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
}