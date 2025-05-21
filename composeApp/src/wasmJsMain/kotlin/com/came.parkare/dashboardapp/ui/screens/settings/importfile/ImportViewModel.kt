package com.came.parkare.dashboardapp.ui.screens.settings.importfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.usecases.GetDefaultTemplatesConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import com.came.parkare.dashboardapp.ui.utils.CommonUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.screen_config_saved_message
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ImportViewModel(
    private val saveScreenConfig: SaveScreenConfig,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val preferences: SharedPreferencesProvider,
    private val validator: ErrorValidator,
    private val getDefaultTemplatesConfig: GetDefaultTemplatesConfig
): ViewModel(){

    private val _state = MutableStateFlow(ImportState())
    val state: StateFlow<ImportState>
        get() = _state.asStateFlow()

    fun initConfig(){
        loadTemplates()
    }

    private fun loadTemplates() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            when(val result = getDefaultTemplatesConfig.invoke()){
                is ServiceResult.Error -> {
                    validator.validate(result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    val newList = result.data.orEmpty().associate { temp -> temp.templateName to
                            CommonUtils().getPrettyJson(temp.screens.map { it.toDto() }) }

                    _state.update { it.copy(templates = newList) }
                    wasmUtilsHandler.showLoading(false)
                }
            }
        }
    }

    fun setValues(fileName: String, fileContent: String){
        viewModelScope.launch {
            _state.update { it.copy(fileName = fileName, contentFile = fileContent) }
        }
    }

    fun saveChanges(fileContent: String) {
        try {
            val configuration = Json.decodeFromString<List<ScreenDto>>(fileContent)
            viewModelScope.launch {
                wasmUtilsHandler.showLoading(true)
                val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
                when(val result = saveScreenConfig.invoke(ipAddress, configuration)){
                    is ServiceResult.Error -> {
                        validator.validate(result.error)
                        wasmUtilsHandler.showLoading(false)
                    }
                    is ServiceResult.Success -> {
                        wasmUtilsHandler.showLoading(false)
                        wasmUtilsHandler.showToastMessage(Res.string.screen_config_saved_message)
                        _state.update { it.copy(contentFile = "", fileName = "") }
                    }
                }
                _state.update { it.copy(isLoading = false) }
            }
        }catch (e: Exception){
            wasmUtilsHandler.showToastMessage(e.message.orEmpty())
        }
    }

    fun setSelectedTemplate(name: String){
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            val template = _state.value.templates[name]
            _state.update { it.copy(selectedTemplate = name) }
            _state.update { it.copy(contentFile = template.orEmpty()) }
            wasmUtilsHandler.showLoading(false)
        }
    }
}