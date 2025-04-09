package com.came.parkare.dashboardapp.ui.screens.settings.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveConnectionConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ImageFileDto
import com.came.parkare.dashboardapp.ui.screens.settings.components.states.FilePickerDialogState
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConnectionViewModel(
    private val getConnectionConfig: GetConnectionConfig,
    private val  saveConnectionConfig: SaveConnectionConfig
): ViewModel() {
    private val _state = MutableStateFlow(ConnectionState())
    val state: StateFlow<ConnectionState>
        get() = _state.asStateFlow()

    init {
        loadConnectionWays()
        loadCurrentConfig()
    }

    private fun loadCurrentConfig() {
        viewModelScope.launch {
            val currentConfig = withContext(Dispatchers.Default){
                getConnectionConfig.invoke()
            }
            when(currentConfig){
                is ServiceResult.Error -> println(currentConfig.error.toString())
                is ServiceResult.Success -> {
                    if (currentConfig.data != null)
                    with(currentConfig.data){
                        _state.update {
                            it.copy(
                                port = port,
                                connectionWay = _state.value.connectionWayOptions.first { it.first == connectionWay },
                                api = terminalApi.orEmpty(),
                                textSizeScale = textSizeScale,
                                terminalIp = terminalIp,
                                showVideoFrame = videoFrame,
                                delayTime = timeDelay,
                                imagesResources = files?.map {
                                    FilePickerDialogState(
                                        fileNames = it.fileName,
                                        fileContentsRaw = it.fileContent,
                                        id = it.id
                                    )
                                }.orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadConnectionWays() {
        _state.update { it.copy(connectionWayOptions = listOf(
            0 to "Pruebas",
            1 to "SignalR",
            2 to "Socket")
        )
        }
    }

    fun setShowVideoFrame(newValue: Boolean) {
        _state.update { it.copy(showVideoFrame = newValue) }
    }

    fun setTerminalIp(newValue: String) {
        _state.update { it.copy(terminalIp = newValue) }
    }

    fun setPort(newValue: Int) {
        _state.update { it.copy(port = newValue) }
    }

    fun setApi(newValue: String) {
        _state.update { it.copy(api = newValue) }
    }

    fun setDelayTime(newValue: Int) {
        _state.update { it.copy(delayTime = newValue) }
    }

    fun textSizeScale(newValue: Int) {
        _state.update { it.copy(textSizeScale = newValue) }
    }

    fun setImages(filesSelected: List<FilePickerDialogState>) {
        _state.update { it.copy(clearSelectedFiles = false) }
        _state.update { it.copy(imagesResources = filesSelected) }
    }

    fun saveChanges() {
        val model = with(_state.value){
            ConnectionConfigDto(
                connectionWay = connectionWay.first,
                textSizeScale = textSizeScale,
                terminalIp = terminalIp,
                videoFrame = showVideoFrame,
                port = port,
                terminalApi = api,
                timeDelay = delayTime,
                apiPort = 2023,
                files = imagesResources.map { ImageFileDto(
                    fileName = it.fileNames,
                    fileContent = it.fileContentsRaw
                ) }
            )
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = withContext(Dispatchers.Default){
                saveConnectionConfig.invoke(model)
            }
            when(result){
                is ServiceResult.Error -> {
                    println(result.error.toString())
                }
                is ServiceResult.Success -> clearForm()
            }
        }
    }

    private fun clearForm() {
        _state.update { it.copy(
            port = 9011,
            connectionWay = Pair(-1, "Select one"),
            api = "signalR",
            imagesResources = emptyList(),
            terminalIp = "",
            delayTime = 5,
            showVideoFrame = false,
            clearSelectedFiles = true
        ) }
    }

    fun setConnectionWay(newValue: String) {
        val connectionWay = _state.value.connectionWayOptions.first { it.second == newValue }
        _state.update { it.copy(connectionWay = connectionWay) }
    }
}