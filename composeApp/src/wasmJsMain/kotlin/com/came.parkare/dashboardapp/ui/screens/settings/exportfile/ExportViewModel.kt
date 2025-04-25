package com.came.parkare.dashboardapp.ui.screens.settings.exportfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.ui.utils.FileDownloader
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class ExportViewModel (
    private val getScreensConfig: GetScreensConfig,
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val appLogger: AppLogger
): ViewModel() {

    private val _state = MutableStateFlow(ExportState())
    val state: StateFlow<ExportState>
        get() = _state.asStateFlow()

    @OptIn(ExperimentalSerializationApi::class)
    fun getCurrentScreenConfig() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            val result = getScreensConfig.invoke()
            when(result){
                is ServiceResult.Error -> {
                    validator.validate(error = result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    val prettyJson = Json {
                        prettyPrint = true
                        prettyPrintIndent = " "
                    }

                    wasmUtilsHandler.showLoading(false)
                    val fileContent = prettyJson.encodeToString(result.data)
                    _state.update { it.copy(fileName = "dashboard_screens.json", contentFile = fileContent) }
                }
            }
        }
    }

    fun setValues(fileName: String, fileContent: String){
        viewModelScope.launch {
            _state.update { it.copy(fileName = fileName, contentFile = fileContent) }
        }
    }

    fun downloadFile(fileName: String, contentFile: String) {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            try {
                FileDownloader.downloadFile(fileName, contentFile)
                delay(1500)
            }catch (e: Exception){
                appLogger.trackError(e)
            }
            wasmUtilsHandler.showToastMessage("File downloaded successfully.")
            wasmUtilsHandler.showLoading(false)
        }
    }
}