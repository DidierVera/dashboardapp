package com.came.parkare.dashboardapp.ui.components.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class AppToastViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {
    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean>
        get() = _state.asStateFlow()
    init {
        wasmUtilsHandler.loadingStatus.onEach { value ->
            _state.update { value }
        }.launchIn(viewModelScope)
    }
}