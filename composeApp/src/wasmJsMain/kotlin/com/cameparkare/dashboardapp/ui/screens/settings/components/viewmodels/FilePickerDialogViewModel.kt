package com.cameparkare.dashboardapp.ui.screens.settings.components.viewmodels

import androidx.lifecycle.ViewModel
import com.cameparkare.dashboardapp.ui.components.PlatformFile
import com.cameparkare.dashboardapp.ui.components.readFileAsByteArray
import com.cameparkare.dashboardapp.ui.screens.settings.components.states.FilePickerDialogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class FilePickerDialogViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(FilePickerDialogState())
    val state: StateFlow<FilePickerDialogState>
        get() = _state.asStateFlow()

    @OptIn(ExperimentalEncodingApi::class)
    suspend fun addFile(file: PlatformFile) {
        val content = readFileAsByteArray(file.file)

        _state.update { it.copy(fileNames = file.file.name) }
        _state.update { it.copy(fileContents = content.decodeToString()) }
        _state.update { it.copy(fileContentsRaw = Base64.encode(content)) }
    }

    fun setPickerVisible(isVisible: Boolean){
        _state.update { it.copy(pickerVisible = isVisible) }
    }

    fun clearPickedValues(){
        _state.update { it.copy(fileNames = "") }
        _state.update { it.copy(fileContents = "") }
    }

    fun setIsFileSelected(isSelected: Boolean){
        _state.update { it.copy(fileSelected = isSelected) }
    }
}