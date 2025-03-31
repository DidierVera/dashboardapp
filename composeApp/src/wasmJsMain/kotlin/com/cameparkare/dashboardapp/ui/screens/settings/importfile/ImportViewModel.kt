package com.cameparkare.dashboardapp.ui.screens.settings.importfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImportViewModel(): ViewModel(){

    private val _state = MutableStateFlow(ImportState())
    val state: StateFlow<ImportState>
        get() = _state.asStateFlow()

    fun setValues(fileName: String, fileContent: String){
        viewModelScope.launch {
            _state.update { it.copy(fileName = fileName, contentFile = fileContent) }
        }
    }

    fun saveChanges() {

    }

    fun getCurrentConfiguration(){

    }

    fun sendConfigurationTo(){

    }

    private fun getDashboardIP(){

    }
}