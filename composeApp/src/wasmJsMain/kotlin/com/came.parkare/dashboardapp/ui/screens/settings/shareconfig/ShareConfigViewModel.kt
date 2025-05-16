package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceList
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceStatus
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShareConfigViewModel(
    private val getDeviceStatus: GetDeviceStatus,
    private val getDeviceList: GetDeviceList,
    private val getScreensConfig: GetScreensConfig


): ViewModel() {

    private val _state = MutableStateFlow(ShareConfigState())
    val state: StateFlow<ShareConfigState>
        get() = _state.asStateFlow()

    init {

    }

}