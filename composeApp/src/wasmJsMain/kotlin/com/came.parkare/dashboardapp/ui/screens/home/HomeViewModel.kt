package com.came.parkare.dashboardapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.GetImages
import com.came.parkare.dashboardapp.domain.usecases.SaveNewTemplate
import com.came.parkare.dashboardapp.domain.usecases.UpdateTemplate
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.toModel
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.config_saved_message
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
    private val validator: ErrorValidator,
    private val homeUtils: HomeUtils,
    private val getImages: GetImages,
    private val saveNewTemplate: SaveNewTemplate,
    private val updateTemplate: UpdateTemplate,
    private val resourceUtils: ResourceUtils
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    fun showRequestLogin(message: String, onAccept:() -> Unit) {
        wasmUtils.showDialogRequestPassword(AppDialogState(
            requirePassword = true,
            onAccept = onAccept,
            message = message
        ))
    }

    init {
        val ownIpAddress = "192.168.101.77"//window.location.hostname
        preferences.put(SELECTED_IP_ADDRESS, ownIpAddress)
        eventTabListener()
        loadImages()
        setTemplateName()
    }

    private fun loadImages() {
        viewModelScope.launch {
            loadConfigImages()
        }
    }

    private fun eventTabListener() {
        homeUtils.isShowingDefaultElements.onEach { display ->
            _state.update { it.copy(displayDefaultElements = display) }
        }.launchIn(viewModelScope)
        homeUtils.isShowingProperties.onEach { display ->
            _state.update { it.copy(displayProperties = display) }
        }.launchIn(viewModelScope)
        homeUtils.isShowingBlankElements.onEach { display ->
            _state.update { it.copy(displayBlankElements = display) }
        }.launchIn(viewModelScope)
        homeUtils.isShowingDefaultScreens.onEach { display ->
            _state.update { it.copy(displayDefaultScreens = display) }
        }.launchIn(viewModelScope)
        homeUtils.blankScreen.onEach { display ->
            _state.update { it.copy(displayBlankScreen = display) }
        }.launchIn(viewModelScope)
    }

    fun displayProperties(){
        homeUtils.showProperties(!_state.value.displayProperties)
    }

    fun displayElements(){
        homeUtils.showElements(!_state.value.displayDefaultElements)
        println("HomeViewModel, displayElements: ${_state.value.displayDefaultElements}")
    }

    fun displayDefaultScreens(){
        homeUtils.showDefaultScreens(!_state.value.displayDefaultScreens)
        println("HomeViewModel, displayDefaultScreens: ${_state.value.displayDefaultScreens}")
    }

    fun displayBlankElement(){
        println("HomeViewModel, displayBlankElement: ${_state.value.displayBlankElements}")

        homeUtils.showBlankElements(!_state.value.displayBlankElements)
    }

    fun addDefaultScreen() {

    }

    fun addBlankScreen() {
        homeUtils.showBlankScreen(!_state.value.displayBlankScreen)
        println("HomeViewModel, addBlankScreen: ${_state.value.displayBlankScreen}")

    }

    fun importConfig() {

    }

    fun setTemplateName() {
        _state.update { it.copy(templateName = "Blank template") }
        resourceUtils.editableTemplate.onEach { template ->
            _state.update { it.copy(templateName = template.templateName) }
        }.launchIn(viewModelScope)
    }

    fun onTemplateNameChange(newValue: String){
        if (newValue.isBlank()) return
        _state.update { it.copy(templateName = newValue) }
        resourceUtils.setEditableTemplate(resourceUtils.editableTemplate.value.copy(templateName = newValue))
    }

    fun saveConfig() {
        viewModelScope.launch {
            wasmUtils.showLoading(true)
            val newTemplate = resourceUtils.editableTemplate.value.copy(templateName = _state.value.templateName)
            val result = if (newTemplate.id == 0L) {
                saveNewTemplate.invoke(newTemplate)
            }else{
                updateTemplate.invoke(newTemplate)
            }
            when (result) {
                is ServiceResult.Error<*> -> {
                    wasmUtils.showLoading(false)
                    validator.validate(result.error)
                }
                is ServiceResult.Success<*> -> {
                    wasmUtils.showLoading(false)
                    wasmUtils.showToastMessage(Res.string.config_saved_message)
                }
            }
        }
    }

    fun onCloseEditor(){
        homeUtils.hideAllTabs()
    }


    private suspend fun loadConfigImages() {
        wasmUtils.showLoading(true)
        when(val images = getImages.invoke()){
            is ServiceResult.Error -> {
                validator.validate(images.error)
                wasmUtils.showLoading(false)
            }
            is ServiceResult.Success -> {
                resourceUtils.setImagesSource( images.data?.map { dto -> dto.toModel() }.orEmpty())
                wasmUtils.showLoading(false)
            }
        }
        wasmUtils.showLoading(true)
        when(val config = getConnectionConfig.invoke()){
            is ServiceResult.Error -> {
                validator.validate(config.error)
                wasmUtils.showLoading(false)
            }
            is ServiceResult.Success -> {
                resourceUtils.setTextSizeScale(config.data?.textSizeScale ?: 10)
                wasmUtils.showLoading(false)
            }
        }
    }
}