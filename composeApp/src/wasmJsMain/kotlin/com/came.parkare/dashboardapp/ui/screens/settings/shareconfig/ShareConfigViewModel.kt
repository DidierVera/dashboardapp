package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceList
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceStatus
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ShareConfigViewModel(
    private val getDeviceStatus: GetDeviceStatus,
    private val getDeviceList: GetDeviceList,
    private val getScreensConfig: GetScreensConfig,
    private val validator: ErrorValidator,
    private val wasmUtils: WasmUtilsHandler


): ViewModel() {

    private val _state = MutableStateFlow(ShareConfigState())
    val state: StateFlow<ShareConfigState>
        get() = _state.asStateFlow()

    fun initConfig() {
        viewModelScope.launch {
            wasmUtils.showLoading(true)
            loadDeviceList()
            loadScreenConfig()
        }
    }

    private fun loadScreenConfig() {
        viewModelScope.launch {
            wasmUtils.showLoading(true)
            when(val screenConfig = getScreensConfig.invoke()){
                is ServiceResult.Error -> {
                    validator.validate(screenConfig.error)
                    wasmUtils.showLoading(false)
                }
                is ServiceResult.Success -> {

                    _state.update {
                        it.copy(configToShare = screenConfig.data?.map { scr ->
                            scr.toModel()
                        } ?: emptyList()) }

                    wasmUtils.showLoading(false)
                }
            }
        }
    }

    private fun runInBackground(block: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            block()
        }
    }
    private suspend fun loadDeviceList() {
        when(val listItems = getDeviceList.invoke()){
            is ServiceResult.Error -> {
                validator.validate(listItems.error)
                wasmUtils.showLoading(false)
            }
            is ServiceResult.Success -> {
                if (!listItems.data.isNullOrEmpty()){
                    _state.update {
                        it.copy(dashboardList = listItems.data.map { item ->
                            DeviceItemState(
                                dashboardIp = item.deviceIp,
                                dashboardName = item.customName,
                            )
                        })
                    }
                }else{
                    _state.update { it.copy(dashboardList = emptyList() )}
                }
                checkDeviceStatus()//startPeriodicStatusCheck()
                wasmUtils.showLoading(false)
            }
        }
    }

    private fun checkDeviceStatus() {
        runInBackground {
            val currentList = _state.value.dashboardList

            currentList.forEach { device ->
                try {

                    when (val result = getDeviceStatus.invoke(device.dashboardIp)) {
                        is ServiceResult.Error -> {
                            updateDeviceStatus(device.dashboardIp, false)
                        }
                        is ServiceResult.Success -> {
                            updateDeviceStatus(device.dashboardIp, result.data ?: false)
                        }
                    }
                } catch (e: Exception) {
                    updateDeviceStatus(device.dashboardIp, false)
                }
            }
        }
    }

    private fun updateDeviceStatus(ip: String, isActive: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                dashboardList = currentState.dashboardList.map { item ->
                    if (item.dashboardIp == ip) {
                        if(!isActive){
                            item.copy(isActive = isActive, isCheckingStatus = false, isChecked = false)
                        }else
                        item.copy(isActive = isActive, isCheckingStatus = false)
                    }
                    else item
                }
            )
        }
    }

    fun setDeviceChecked(ipAddress: String, newValue: Boolean){
        _state.update { currentState -> currentState.copy(
            dashboardList = currentState.dashboardList.map { item ->
                if(item.dashboardIp == ipAddress) item.copy(isChecked = newValue)
                else item
            }
        ) }
        if (_state.value.dashboardList.any { it.isChecked }){
            _state.update { it.copy(allowedToShare = true) }
        }else{
            _state.update { it.copy(allowedToShare = false) }
        }
    }

    fun selectScreen(screen: ScreenModel){
        if (_state.value.screenViewer == screen.screenId){
            _state.update { it.copy(screenViewer = null, elementsByScreen = emptyList()) }
        }else{
            _state.update { it.copy(screenViewer = screen.screenId, elementsByScreen = screen.elements)}
        }
    }

    fun launchPasswordRequest(onClick: () -> Unit){

    }

    fun shareConfig() {

    }
}