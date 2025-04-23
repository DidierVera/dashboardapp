package com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.models.DeviceModel
import com.came.parkare.dashboardapp.domain.usecases.DeleteDevice
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceList
import com.came.parkare.dashboardapp.domain.usecases.SaveNewDevice
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.general_configuration_title
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardListViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val getDeviceList: GetDeviceList,
    private val saveNewDevice: SaveNewDevice,
    private val deleteDevice: DeleteDevice,
    private val validator: ErrorValidator
):ViewModel() {
    private val _state: MutableStateFlow<DashboardListState> = MutableStateFlow(DashboardListState())
    val state: StateFlow<DashboardListState>
        get() = _state.asStateFlow()

    fun initTab(){
        _state.update { DashboardListState() }
        getCurrentItems()
    }

    private fun getCurrentItems() {
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            when(val result = getDeviceList.invoke()){
                is ServiceResult.Error -> {
                    validator.validate(result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    _state.update { it.copy(
                        currentItems = result.data.orEmpty().map { device ->
                            DashboardListState(
                                dashboardIp = device.deviceIp,
                                terminalIp = device.terminalIp,
                                customName = device.customName,
                                id = device.id
                        ) }
                    ) }
                    wasmUtilsHandler.showLoading(false)

                }
            }
        }
    }

    fun saveNewItem(){
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            val result = saveNewDevice.invoke(DeviceModel(
                terminalIp = _state.value.terminalIp,
                deviceIp = _state.value.dashboardIp,
                customName = _state.value.customName.orEmpty(),
                id = 0
            ))

            when(result){
                is ServiceResult.Error -> {
                    validator.validate(result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    wasmUtilsHandler.showToastMessage("Device saved successfully")
                    wasmUtilsHandler.showLoading(false)
                    getCurrentItems()
                    clearFields()
                }
            }
        }

    }

    private fun clearFields() {
        _state.update { it.copy(dashboardIp = "", customName = null, terminalIp = null,
            isSaveEnabled = false
        ) }
    }

    fun setDashboardIp(value: String){
        _state.update { it.copy(dashboardIp = value) }
        validateForm()
    }

    fun setCustomName(value: String?){
        _state.update { it.copy(customName = value) }
    }

    fun setTerminalIp(value: String?){
        _state.update { it.copy(terminalIp = value) }
        validateForm()
    }

    private fun validateForm(){
        val enableButton = if(isValidIPAddressRegex(_state.value.dashboardIp)){
            if (!_state.value.terminalIp.isNullOrBlank()){
                isValidIPAddressRegex(_state.value.terminalIp.orEmpty())
            }else if(_state.value.currentItems.find { x -> x.dashboardIp == _state.value.dashboardIp } != null) {
                wasmUtilsHandler.showToastMessage("This Dashboard IP already exist")
                false
            }else{
                true
            }
        }
        else{
            false
        }

        _state.update { it.copy(isSaveEnabled = enableButton) }
    }

    private fun isValidIPAddressRegex(ip: String): Boolean {
        val ipv4Pattern = """^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$""".toRegex()
        val ipv6Pattern = """^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$""".toRegex()
        return ipv4Pattern.matches(ip) || ipv6Pattern.matches(ip)
    }

    fun removeItem(deviceModel: DashboardListState){

        wasmUtilsHandler.showDialogMessage(model = AppDialogState(
            message = "Are you sure you want to delete the item: ${deviceModel.dashboardIp}",
            onAccept = {
                viewModelScope.launch {
                    callServiceAndDeleteItem(deviceModel)
                }
            }
        ))
    }

    private suspend fun callServiceAndDeleteItem(deviceModel: DashboardListState) {
        val result = deleteDevice.invoke(DeviceModel(
            id = deviceModel.id, terminalIp = deviceModel.terminalIp,
            customName = deviceModel.customName.orEmpty(), deviceIp = deviceModel.dashboardIp
        ))
        when (result){
            is ServiceResult.Error -> {
                wasmUtilsHandler.showLoading(false)
                validator.validate(result.error)
            }
            is ServiceResult.Success -> {
                wasmUtilsHandler.showLoading(false)
                getCurrentItems()
                wasmUtilsHandler.showToastMessage("Device: ${deviceModel.dashboardIp} Eliminado correctamente")
            }
        }
    }
}