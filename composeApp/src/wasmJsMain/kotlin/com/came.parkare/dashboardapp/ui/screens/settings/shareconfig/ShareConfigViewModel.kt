package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceList
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceStatus
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
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
    }

    private var statusCheckJob: Job? = null

    private fun startPeriodicStatusCheck() {
        stopPeriodicStatusCheck() // Cancel any existing job

        statusCheckJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                checkDeviceStatus()
                delay(120_000) // 12 seconds delay
            }
        }
    }

    private fun stopPeriodicStatusCheck() {
        statusCheckJob?.cancel()
        statusCheckJob = null
    }

    // Call this when your component is being disposed
    fun cleanup() {
        stopPeriodicStatusCheck()
    }
}