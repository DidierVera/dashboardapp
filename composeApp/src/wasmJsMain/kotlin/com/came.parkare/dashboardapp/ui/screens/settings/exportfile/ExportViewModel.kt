package com.came.parkare.dashboardapp.ui.screens.settings.exportfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.domain.usecases.GetDefaultTemplatesConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import com.came.parkare.dashboardapp.ui.utils.CommonUtils
import com.came.parkare.dashboardapp.ui.utils.FileDownloader
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class ExportViewModel (
    private val getScreensConfig: GetScreensConfig,
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val appLogger: AppLogger,
    private val getDefaultTemplatesConfig: GetDefaultTemplatesConfig
): ViewModel() {

    private val _state = MutableStateFlow(ExportState())
    val state: StateFlow<ExportState>
        get() = _state.asStateFlow()

    fun initConfig(){
        _state.update { it.copy(templates = mapOf()) }
        getCurrentScreenConfig()
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
                    val newList = _state.value.templates.toMutableMap()
                    newList.putAll(result.data.orEmpty().associate { temp -> temp.templateName to
                            CommonUtils().getPrettyJson(temp.screens.map { it.toDto() }) })

                    _state.update { it.copy(templates = newList) }
                    wasmUtilsHandler.showLoading(false)
                }
            }
        }
    }

    private fun getCurrentScreenConfig() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            when(val result = getScreensConfig.invoke()){
                is ServiceResult.Error -> {
                    validator.validate(error = result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {

                    wasmUtilsHandler.showLoading(false)
                    val fileContent = CommonUtils().getPrettyJson(result.data)
                    _state.update { it.copy(selectedTemplate = _state.value.currentConfigLabel) }
                    _state.update { it.copy(templates = mapOf(_state.value.currentConfigLabel to fileContent)) }
                    _state.update { it.copy(contentFile = fileContent) }
                }
            }
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

    fun setValues(fileContent: String){
        viewModelScope.launch {
            _state.update { it.copy(contentFile = fileContent) }
        }
    }

    fun downloadFile(contentFile: String) {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            try {
                FileDownloader.downloadFile("dashboard_screens.json", contentFile)
                delay(1500)
            }catch (e: Exception){
                appLogger.trackError(e)
            }
            wasmUtilsHandler.showToastMessage("File downloaded successfully.")
            wasmUtilsHandler.showLoading(false)
        }
    }
}