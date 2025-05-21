package com.came.parkare.dashboardapp.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceList
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.connection_option
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.edit_current_config_option
import dashboardapp.composeapp.generated.resources.export_option
import dashboardapp.composeapp.generated.resources.ic_connection
import dashboardapp.composeapp.generated.resources.ic_download
import dashboardapp.composeapp.generated.resources.ic_monitor
import dashboardapp.composeapp.generated.resources.ic_share
import dashboardapp.composeapp.generated.resources.ic_testing
import dashboardapp.composeapp.generated.resources.ic_upload
import dashboardapp.composeapp.generated.resources.ico_edit_file
import dashboardapp.composeapp.generated.resources.import_export_option
import dashboardapp.composeapp.generated.resources.import_option
import dashboardapp.composeapp.generated.resources.share_config_option
import dashboardapp.composeapp.generated.resources.testing_option
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingViewModel(
    private val preferences: WasmSharedPreferencesProvider,
    private val getDeviceList: GetDeviceList,
    private val validator: ErrorValidator
): ViewModel() {

    private val _optionsState = MutableStateFlow<List<MenuOptionState>>(listOf())
    val optionsState: StateFlow<List<MenuOptionState>>
        get() = _optionsState.asStateFlow()

    private val _selectedOption = MutableStateFlow(MenuOptionState())
    val selectedOption: StateFlow<MenuOptionState>
        get() = _selectedOption.asStateFlow()

    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState: StateFlow<SettingsState>
        get() = _settingsState.asStateFlow()

    private val _ipAddresses = MutableStateFlow<List<String>>(emptyList())
    val ipAddresses: StateFlow<List<String>>
        get() = _ipAddresses.asStateFlow()

    private val _refreshState = MutableStateFlow(0)
    val refreshState: StateFlow<Int>
        get() = _refreshState.asStateFlow()

    init {
        val ownIpAddress = "192.168.209.105"//window.location.hostname TODO: uncomment the hostname
        preferences.put(SELECTED_IP_ADDRESS, ownIpAddress)

        loadLeftPanelOptions()
        loadIpAddress()
        getIpAddress()
    }

    private fun loadIpAddress() {
        val ip = window.location.hostname
        val currentIp = preferences.get(SELECTED_IP_ADDRESS, ip)
        setIpAddress(currentIp)
    }

    fun setIpAddress(ip: String){
        _settingsState.update { it.copy(ipSelected = ip) }
        preferences.put(SELECTED_IP_ADDRESS, ip)
        resetComponent()
    }

    private fun loadLeftPanelOptions() {
        _optionsState.update {
            listOf(
                MenuOptionState(
                    iconRes = Res.drawable.ic_connection,
                    nameRes = Res.string.connection_option,
                    isSelected = true
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_monitor,
                    nameRes = Res.string.dashboard_list_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_download,
                    nameRes = Res.string.export_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_upload,
                    nameRes = Res.string.import_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ico_edit_file,
                    nameRes = Res.string.edit_current_config_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_share,
                    nameRes = Res.string.share_config_option,
                    isSelected = false
                ),
//                MenuOptionState(
//                    iconRes = Res.drawable.ic_testing,
//                    nameRes = Res.string.testing_option,
//                    isSelected = false
//                )
            )
        }
    }

    fun selectItem(option: MenuOptionState) {
        viewModelScope.launch {
            _selectedOption.update { option }
            _optionsState.update { currentList ->
                currentList.map { item ->
                    if (item.nameRes == option.nameRes) {
                        item.copy(isSelected = true)
                    } else {
                        item.copy(isSelected = false)
                    }
                }
            }
        }
    }

    private fun resetComponent(){
        val newValue = refreshState.value + 1
        _refreshState.update { newValue }
    }

    private fun getIpAddress(){
        viewModelScope.launch {
            when(val result = getDeviceList.invoke()){
                is ServiceResult.Error -> validator.validate(result.error)
                is ServiceResult.Success -> {
                    _ipAddresses.update { result.data.orEmpty().map { it.deviceIp } }
                }
            }
        }
    }
}