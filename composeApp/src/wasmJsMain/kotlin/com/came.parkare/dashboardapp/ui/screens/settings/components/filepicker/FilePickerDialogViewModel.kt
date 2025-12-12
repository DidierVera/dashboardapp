package com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.ui.components.PlatformFile
import com.came.parkare.dashboardapp.ui.components.readFileAsByteArray
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class FilePickerDialogViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(FilePickerDialogState())
    private val _multipleFiles = MutableStateFlow<List<FilePickerDialogState>>(emptyList())

    val multipleFiles: StateFlow<List<FilePickerDialogState>>
        get() = _multipleFiles.asStateFlow()

    val state: StateFlow<FilePickerDialogState>
        get() = _state.asStateFlow()

    @OptIn(ExperimentalEncodingApi::class)
    suspend fun addFile(file: PlatformFile) {
        val content = readFileAsByteArray(file.file)

        _state.update { it.copy(fileNames = file.file.name) }
        _state.update { it.copy(fileContents = content.decodeToString()) }
        _state.update { it.copy(fileContentsRaw = Base64.encode(content)) }
    }

    @OptIn(ExperimentalEncodingApi::class)
    suspend fun addFiles(files: List<PlatformFile>) {
        val stateFiles = mutableListOf<FilePickerDialogState>()
        for (file in files){
            val content = readFileAsByteArray(file.file)
            stateFiles.add(
                FilePickerDialogState(
                    fileNames = file.file.name,
                    fileContents = content.decodeToString(),
                    fileContentsRaw = Base64.encode(content)
                )
            )
        }
        _multipleFiles.update { stateFiles }
    }


    fun setPickerVisible(isVisible: Boolean){
        _state.update { it.copy(pickerVisible = isVisible) }
    }

    fun clearPickedValues(){
        _state.update { it.copy(fileNames = "") }
        _state.update { it.copy(fileContents = "") }
        _multipleFiles.update { emptyList() }
    }

    fun setIsFileSelected(isSelected: Boolean){
        _state.update { it.copy(fileSelected = isSelected) }
    }
}