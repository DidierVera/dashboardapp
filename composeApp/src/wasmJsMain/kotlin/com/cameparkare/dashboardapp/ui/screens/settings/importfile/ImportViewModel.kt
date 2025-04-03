package com.cameparkare.dashboardapp.ui.screens.settings.importfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
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
            saveScreenConfig.invoke(ipAddress, configuration)
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