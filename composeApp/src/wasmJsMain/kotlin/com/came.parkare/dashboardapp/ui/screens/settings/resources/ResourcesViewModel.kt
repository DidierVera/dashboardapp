package com.came.parkare.dashboardapp.ui.screens.settings.resources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetImages
import com.came.parkare.dashboardapp.domain.usecases.SaveFonts
import com.came.parkare.dashboardapp.domain.usecases.SaveImages
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto
import com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker.FilePickerDialogState
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.config_saved_message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResourcesViewModel(
    private val getImages: GetImages,
    private val saveImages: SaveImages,
    private val saveFonts: SaveFonts,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val validator: ErrorValidator
): ViewModel() {

    private val _state = MutableStateFlow(ResourcesState())
    val state: StateFlow<ResourcesState>
        get() = _state.asStateFlow()

    fun initTab() {
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            delay(600)
            val result = withContext(Dispatchers.Default) {
                getImages.invoke()
            }
            when(result) {
                is ServiceResult.Error -> {
                    wasmUtilsHandler.showLoading(false)
                    validator.validate(result.error)
                }
                is ServiceResult.Success -> {
                    if (result.data != null){
                        _state.update { src -> src.copy(
                            imagesResources = result.data.map {
                                FilePickerDialogState(
                                    fileNames = it.fileName,
                                    fileContentsRaw = it.fileContent,
                                    id = it.id
                                )
                            }
                        ) }
                    }else _state.update { it.copy(imagesResources = emptyList()) }
                }
            }

            wasmUtilsHandler.showLoading(false)
        }
    }

    fun removeFont(file: FilePickerDialogState) {
        _state.update { it.copy(clearSelectedFiles = true) }
        _state.update { it.copy(fontResources = it.fontResources.filter { fnt -> fnt != file }) }
    }

    private fun upsertFiles(
        current: List<FilePickerDialogState>,
        incoming: List<FilePickerDialogState>
    ): List<FilePickerDialogState> {
        val result = current.toMutableList()
        incoming.forEach { newFile ->
            val index = result.indexOfFirst { it.fileNames == newFile.fileNames }
            when {
                index == -1 -> result.add(newFile)
                result[index].fileContentsRaw != newFile.fileContentsRaw -> result[index] = newFile
            }
        }
        return result
    }

    fun setImages(filesSelected: List<FilePickerDialogState>) {
        _state.update {
            it.copy(
                clearSelectedFiles = false,
                imagesResources = upsertFiles(it.imagesResources, filesSelected)
            )
        }
    }

    fun setFonts(filesSelected: List<FilePickerDialogState>) {
        _state.update {
            it.copy(
                clearSelectedFiles = false,
                fontResources = upsertFiles(it.fontResources, filesSelected)
            )
        }
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            //upload file
            val result = saveFonts.invoke(filesSelected.map { ResourceFileDto(
                fileName = it.fileNames,
                fileContentArray = it.fileContentByteArray,
                fileContent = it.fileContentsRaw
            ) })
            when(result){
                is ServiceResult.Error -> {
                    wasmUtilsHandler.showLoading(false)
                    validator.validate(result.error)
                }
                is ServiceResult.Success -> {
                    wasmUtilsHandler.showLoading(false)
                }
            }
        }
    }

    fun downloadImage(image: FilePickerDialogState) {
        viewModelScope.launch {
            downloadFile(image.fileNames, image.fileContentsRaw)
        }
    }


    fun removeImage(image: FilePickerDialogState) {
        _state.update { it.copy(clearSelectedFiles = true) }
        _state.update { it.copy(imagesResources = it.imagesResources.filter { img -> img != image }) }
    }

    fun saveChanges() {

        val files = _state.value.imagesResources.map { ResourceFileDto(
            fileName = it.fileNames,
            fileContent = it.fileContentsRaw
        ) }
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            val result = withContext(Dispatchers.Default){
                saveImages.invoke(files)
            }
            when(result){
                is ServiceResult.Error -> {
                    validator.validate(result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    wasmUtilsHandler.showToastMessage(Res.string.config_saved_message)
                    loadImages()
                    wasmUtilsHandler.showLoading(false)
                }
            }
        }

    }
}

// wasmJsMain
@JsFun("""
    function(fileName, base64) {
        const byteCharacters = atob(base64);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'image/png' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = fileName;
        a.click();
        URL.revokeObjectURL(url);
    }
""")
external fun downloadFile(fileName: String, base64: String)