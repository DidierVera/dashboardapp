package com.came.parkare.dashboardapp.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.SELECTED_IP_ADDRESS
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.connection_option
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.export_option
import dashboardapp.composeapp.generated.resources.ic_connection
import dashboardapp.composeapp.generated.resources.ic_download
import dashboardapp.composeapp.generated.resources.ic_monitor
import dashboardapp.composeapp.generated.resources.ic_share
import dashboardapp.composeapp.generated.resources.ic_testing
import dashboardapp.composeapp.generated.resources.ic_upload
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
    private val preferences: WasmSharedPreferencesProvider
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

    init {
        loadLeftPanelOptions()
        loadIpAddress()
    }

    private fun loadIpAddress() {
        val ip = window.location.hostname
        val currentIp = preferences.get(SELECTED_IP_ADDRESS, ip)
        _settingsState.update { it.copy(ipSelected = currentIp) }
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
                    iconRes = Res.drawable.ic_upload,
                    nameRes = Res.string.import_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_download,
                    nameRes = Res.string.export_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_share,
                    nameRes = Res.string.share_config_option,
                    isSelected = false
                ),
                MenuOptionState(
                    iconRes = Res.drawable.ic_testing,
                    nameRes = Res.string.testing_option,
                    isSelected = false
                )
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
}