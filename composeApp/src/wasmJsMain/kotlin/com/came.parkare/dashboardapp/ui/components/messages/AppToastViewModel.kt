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
import org.jetbrains.compose.resources.StringResource

class AppToastViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {
    private val _state = MutableStateFlow(AppToastState())
    val state: StateFlow<AppToastState>
        get() = _state.asStateFlow()
    init {
        toastString()
        toastResString()
    }

    private fun toastResString() {
        wasmUtilsHandler.toastResMessage.onEach { value ->
            if (value != null){
                showStringResMessage(value)
            }
        }.launchIn(viewModelScope)
    }

    private fun showStringResMessage(resValue: StringResource) {
        _state.update { it.copy(messageRes = resValue) }
    }

    private fun toastString() {
        wasmUtilsHandler.toastMessage.onEach { value ->
            if(value.isNotBlank()){
                showToastMessage(value)
            }
        }.launchIn(viewModelScope)
    }

    private fun showToastMessage(newMessage: String) {
        _state.update { it.copy(message = newMessage) }
        _state.update { it.copy(showMessage = true) }
        _state.update { it.copy(timer = 250f) }
    }

    fun setMessage(message: String){
        _state.update { it.copy(messageRes = null) }
        showToastMessage(message)
    }

    fun hideMessage(){
        _state.update { it.copy(showMessage = false) }
        _state.update { it.copy(message = "") }
    }

    fun setTimer(newValue: Float){
        _state.update { it.copy(timer = newValue) }
    }
}