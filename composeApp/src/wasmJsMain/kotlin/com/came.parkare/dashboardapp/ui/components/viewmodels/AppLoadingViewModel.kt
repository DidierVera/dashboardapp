package com.came.parkare.dashboardapp.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.ui.components.loading.LoadingHandler
import com.came.parkare.dashboardapp.ui.screens.settings.connection.ConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppLoadingViewModel(
    private val loadingHandler: LoadingHandler
): ViewModel() {
    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean>
        get() = _state.asStateFlow()
    init {
        loadingHandler.loadingStatus.onEach { value ->
            _state.update { value }
        }.launchIn(viewModelScope)
    }
}