package com.came.parkare.dashboardapp.ui.screens.settings.importfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ImportViewModel(
    private val saveScreenConfig: SaveScreenConfig
): ViewModel(){

    private val _state = MutableStateFlow(ImportState())
    val state: StateFlow<ImportState>
        get() = _state.asStateFlow()

    fun setValues(fileName: String, fileContent: String){
        viewModelScope.launch {
            _state.update { it.copy(fileName = fileName, contentFile = fileContent) }
        }
    }

    fun saveChanges(fileContent: String) {
        val ipAddress = window.location.hostname
        val configuration = Json.decodeFromString<List<ScreenDto>>(fileContent)
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = saveScreenConfig.invoke(ipAddress, configuration)
            when(result){
                is ServiceResult.Error -> {
                    when(result.error){
                        ErrorTypeClass.CanNoAccessToConfigFile -> TODO()
                        ErrorTypeClass.ConfigFileNotExist -> TODO()
                        is ErrorTypeClass.GeneralException -> {
                            _state.update { it.copy(contentFile = result.error.messageError.orEmpty(), fileName = "") }
                        }
                        ErrorTypeClass.NotSocketResponse -> TODO()
                        ErrorTypeClass.SocketConnectionError -> TODO()
                        ErrorTypeClass.WrongConfigFile -> TODO()
                        is ErrorTypeClass.ResourceGeneralException -> TODO()
                    }
                }
                is ServiceResult.Success -> {
                    _state.update { it.copy(contentFile = "", fileName = "") }
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun getCurrentConfiguration(){

    }

    fun sendConfigurationTo(){

    }

    private fun getDashboardIP(){

    }
}