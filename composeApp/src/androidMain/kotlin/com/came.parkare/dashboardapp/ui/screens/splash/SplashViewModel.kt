package com.came.parkare.dashboardapp.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.DeviceUtils
import com.came.parkare.dashboardapp.domain.usecases.ConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.FtpServerConfiguration
import com.came.parkare.dashboardapp.domain.usecases.GetDefaultImages
import com.came.parkare.dashboardapp.domain.usecases.GetScreenConfigurations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val deviceUtils: DeviceUtils,
    private val getScreenConfigurations: GetScreenConfigurations,
    private val connectionConfig: ConnectionConfig,
    private val ftpServerConfiguration: FtpServerConfiguration,
    private val getDefaultImages: GetDefaultImages
): ViewModel() {

    private val _appVersion = MutableStateFlow("1.0")
    val appVersion: StateFlow<String>
        get() = _appVersion.asStateFlow()

    private val _progressPhase = MutableStateFlow("Initializing...")
    val progressPhase: StateFlow<String>
        get() = _progressPhase.asStateFlow()

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean>
        get() = _isReady.asStateFlow()

    init {
        viewModelScope.launch {
            val version = deviceUtils.getAppVersion()
            if (version != null)
                _appVersion.update { version.versionName }
        }
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {
            _progressPhase.update { "Loading screen configuration..." }
            getScreenConfigurations.invoke()

            _progressPhase.update { "Setting up connection..." }
            connectionConfig.invoke()

            _progressPhase.update { "Setting up FTP server..." }
            ftpServerConfiguration.invoke()

            _progressPhase.update { "Loading resources..." }
            getDefaultImages.invoke()

            _progressPhase.update { "Ready" }
            _isReady.update { true }
        }
    }
}