package com.came.parkare.dashboardapp.ui.screens.settings.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveConnectionConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
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

class ConnectionViewModel(
    private val getConnectionConfig: GetConnectionConfig,
    private val  saveConnectionConfig: SaveConnectionConfig,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val validator: ErrorValidator
): ViewModel() {
    private val _state = MutableStateFlow(ConnectionState())
    val state: StateFlow<ConnectionState>
        get() = _state.asStateFlow()

    fun initTab(){
        _state.update { ConnectionState() }
        loadConnectionWays()
        loadCurrentConfig()
    }

    private fun loadCurrentConfig() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            delay(1600)
            val currentConfig = withContext(Dispatchers.Default){
                getConnectionConfig.invoke()
            }
            when(currentConfig){
                is ServiceResult.Error -> {
                    wasmUtilsHandler.showLoading(false)
                    validator.validate(currentConfig.error)
                }
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
                                brightnessDelay = activeLowBrightnessTime ?: 2,
                                showBrightnessMode = autoBrightness == true,
                                carCounterReset = carCounterReset ?: 1,
                                showCarCounter = showCarCounter == true
                            )
                        }
                    }
                    wasmUtilsHandler.showLoading(false)
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

    fun setBrightnessMode(newValue: Boolean){
        _state.update { it.copy(showBrightnessMode = newValue) }
    }

    fun setShowCarCounter(newValue: Boolean) {
        _state.update { it.copy(showCarCounter = newValue) }
    }

    fun setBrightnessDelay(newValue: Int){
        _state.update { it.copy(brightnessDelay = newValue) }
    }

    fun setCarCounterReset(newValue: Int){
        _state.update { it.copy(carCounterReset = newValue) }
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

    fun saveChanges() {
        val model = getConnectionConfigModel()
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            val result = withContext(Dispatchers.Default){
                saveConnectionConfig.invoke(model)
            }
            when(result){
                is ServiceResult.Error -> {
                    validator.validate(result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    wasmUtilsHandler.showToastMessage(Res.string.config_saved_message)
                    loadCurrentConfig()
                    wasmUtilsHandler.showLoading(false)
                }
            }
        }
    }

    private fun getConnectionConfigModel() = with(_state.value){
        ConnectionConfigDto(connectionWay = connectionWay.first, textSizeScale = textSizeScale,
            terminalIp = terminalIp, videoFrame = showVideoFrame, port = port,
            terminalApi = api, timeDelay = delayTime, apiPort = 2023,
            activeLowBrightnessTime = brightnessDelay, autoBrightness = showBrightnessMode,
            carCounterReset = carCounterReset, showCarCounter = showCarCounter
        )
    }

    fun setConnectionWay(newValue: String) {
        val connectionWay = _state.value.connectionWayOptions.first { it.second == newValue }
        _state.update { it.copy(connectionWay = connectionWay) }
    }
}