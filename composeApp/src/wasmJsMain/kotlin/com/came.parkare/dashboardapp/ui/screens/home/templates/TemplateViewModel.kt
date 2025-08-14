package com.came.parkare.dashboardapp.ui.screens.home.templates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.usecases.DeleteTemplate
import com.came.parkare.dashboardapp.domain.usecases.GetDefaultTemplatesConfig
import com.came.parkare.dashboardapp.domain.usecases.GetTemplates
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.configuration_shared_successfully
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TemplateViewModel(
    private val getTemplates: GetTemplates,
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val resourceUtils: ResourceUtils,
    private val getDefaultTemplatesConfig: GetDefaultTemplatesConfig,
    private val deleteTemplate: DeleteTemplate
): ViewModel() {
    private val _state = MutableStateFlow(TemplateState())
    val state: StateFlow<TemplateState>
        get() = _state.asStateFlow()

    fun initScreen(){
        viewModelScope.launch {
            loadExistingItems()
            loadDefaultTemplates()
        }
    }

    private suspend fun loadDefaultTemplates() {
        wasmUtilsHandler.showLoading(true)
        when(val result = getDefaultTemplatesConfig.invoke()){
            is ServiceResult.Error ->  {
                validator.validate(result.error)
                wasmUtilsHandler.showLoading(false)
            }
            is ServiceResult.Success -> {
                _state.update { it.copy(defaultTemplates = result.data.orEmpty()) }
                wasmUtilsHandler.showLoading(false)
            }
        }
    }

    private suspend fun loadExistingItems() {
        wasmUtilsHandler.showLoading(true)
        when(val result = getTemplates.invoke()){
            is ServiceResult.Error -> {
                validator.validate(result.error)
                wasmUtilsHandler.showLoading(false)
            }
            is ServiceResult.Success -> {
                _state.update { it.copy(existingItems = result.data.orEmpty()) }
                wasmUtilsHandler.showLoading(false)
            }
        }
    }

    fun deleteTemplate(deleteMessage: String, template: ConfigTemplateModel){
        val model = AppDialogState(
            message = deleteMessage,
            onAccept = {
                viewModelScope.launch {
                    wasmUtilsHandler.showLoading(true)

                    when (val result = deleteTemplate.invoke(template)){
                        is ServiceResult.Error -> {
                            validator.validate(result.error)
                            wasmUtilsHandler.showLoading(false)
                        }
                        is ServiceResult.Success -> {
                            wasmUtilsHandler.showToastMessage(Res.string.configuration_shared_successfully)
                            initScreen()
                            wasmUtilsHandler.showLoading(false)
                        }
                    }
                }
            },
            isAcceptButtonActive = true
        )
        wasmUtilsHandler.showDialogMessage(model)
    }

    fun setEditableTemplate(template: ConfigTemplateModel, onRedirect: () -> Unit){
        resourceUtils.setEditableTemplate(template)
        onRedirect.invoke()
    }
}