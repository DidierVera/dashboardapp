package com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardListViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler
):ViewModel() {
    private val _state: MutableStateFlow<DashboardListState> = MutableStateFlow(DashboardListState())
    val state: StateFlow<DashboardListState>
        get() = _state.asStateFlow()

    init {
        getCurrentItems()
    }

    private fun getCurrentItems() {

    }

    fun saveNewItem(){
        wasmUtilsHandler.showToastMessage("Momento de guardar todo con: ${_state.value.dashboardIp}; ${_state.value.customName} y ${_state.value.terminalIp}")

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
            }else{
                true
            }
        }else{
            false
        }

        _state.update { it.copy(isSaveEnabled = enableButton) }
    }

    private fun isValidIPAddressRegex(ip: String): Boolean {
        val ipv4Pattern = """^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$""".toRegex()
        val ipv6Pattern = """^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$""".toRegex()
        return ipv4Pattern.matches(ip) || ipv6Pattern.matches(ip)
    }

    private fun enableSaveButton(value: Boolean){
        _state.update { it.copy(isSaveEnabled = value) }
    }
}