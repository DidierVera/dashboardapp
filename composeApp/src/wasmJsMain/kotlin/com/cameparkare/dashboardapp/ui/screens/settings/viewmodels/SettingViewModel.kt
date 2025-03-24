package com.cameparkare.dashboardapp.ui.screens.settings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cameparkare.dashboardapp.ui.screens.settings.states.SettingOptionsState
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.compose_multiplatform
import dashboardapp.composeapp.generated.resources.connection_option
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.import_export_option
import dashboardapp.composeapp.generated.resources.testing_option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingViewModel: ViewModel() {

    private val _optionsState = MutableStateFlow<List<SettingOptionsState>>(listOf())
    val optionsState: StateFlow<List<SettingOptionsState>>
        get() = _optionsState.asStateFlow()

    init {
        _optionsState.update {
            listOf(
                SettingOptionsState(
                    iconRes = Res.drawable.compose_multiplatform,
                    nameRes = Res.string.connection_option,
                    isSelected = false
                ),
                SettingOptionsState(
                    iconRes = Res.drawable.compose_multiplatform,
                    nameRes = Res.string.dashboard_list_option,
                    isSelected = false
                ),
                SettingOptionsState(
                    iconRes = Res.drawable.compose_multiplatform,
                    nameRes = Res.string.import_export_option,
                    isSelected = false
                ),
                SettingOptionsState(
                    iconRes = Res.drawable.compose_multiplatform,
                    nameRes = Res.string.testing_option,
                    isSelected = false
                )
            )
        }
    }

    fun selectItem(option: SettingOptionsState) {
        viewModelScope.launch {
            _optionsState.update { currentList ->
                currentList.map { item ->
                    if (item.nameRes == option.nameRes) {
                        item.copy(isSelected = true) // mark only the selected item as true
                    } else {
                        item.copy(isSelected = false) // ensure all others are false (single selection)
                        // OR item // if you want to keep their current state (for multi-selection)
                    }
                }
            }
        }
    }
}