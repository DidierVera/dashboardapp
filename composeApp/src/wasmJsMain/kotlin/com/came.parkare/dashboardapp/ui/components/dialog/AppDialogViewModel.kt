package com.came.parkare.dashboardapp.ui.components.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class AppDialogViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {
    private val _state = MutableStateFlow(AppDialogState())
    val state: StateFlow<AppDialogState>
        get() = _state.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean>
        get() = _showDialog.asStateFlow()

    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String>
        get() = _passwordState.asStateFlow()

    init {
        settingMessage()
    }

    private fun settingMessage() {
        wasmUtilsHandler.dialogMessage.onEach { model ->
            if (model != AppDialogState()){
                _state.update { AppDialogState() }
                _showDialog.update { true }
                _state.update { model }
            }
        }.launchIn(viewModelScope)
    }

    fun hideDialog(){
        _showDialog.update { false }
    }

    fun setPassword(newValue: String){
        _passwordState.update { newValue }
    }

}