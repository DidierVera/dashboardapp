package com.came.parkare.dashboardapp.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.utils.DeviceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(private val deviceUtils: DeviceUtils): ViewModel() {

    private val _appVersion = MutableStateFlow("1.0")
    val appVersion:StateFlow<String>
        get() = _appVersion.asStateFlow()

    init {
        viewModelScope.launch {
            val version = deviceUtils.getAppVersion()
            if (version != null)
                _appVersion.update { version.versionName }
        }
    }
}