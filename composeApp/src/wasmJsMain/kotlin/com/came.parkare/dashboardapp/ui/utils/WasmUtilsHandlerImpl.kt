package com.came.parkare.dashboardapp.ui.utils

import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.StringResource

class WasmUtilsHandlerImpl: WasmUtilsHandler {
    private val _loadingStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _toastMessage: MutableStateFlow<String> = MutableStateFlow("")
    private val _toastResMessage: MutableStateFlow<StringResource?> = MutableStateFlow(null)
    private val _dialogMessage: MutableStateFlow<AppDialogState> = MutableStateFlow(AppDialogState())

    override val loadingStatus: StateFlow<Boolean>
        get() = _loadingStatus.asStateFlow()

    override val toastMessage: StateFlow<String>
        get() = _toastMessage.asStateFlow()
    override val toastResMessage: StateFlow<StringResource?>
        get() = _toastResMessage.asStateFlow()
    override val dialogMessage: StateFlow<AppDialogState>
        get() = _dialogMessage.asStateFlow()

    override fun showToastMessage(message: String) {
        _toastMessage.update { "" }
        _toastMessage.update { message }
    }

    override fun showToastMessage(stringRes: StringResource?) {
        _toastResMessage.update { null }
        _toastResMessage.update { stringRes }
    }

    override fun showLoading(value: Boolean) {
        _loadingStatus.update { value }
    }

    override fun showDialogMessage(model: AppDialogState) {
        _dialogMessage.update { model }
    }

    override fun showDialogRequestPassword(model: AppDialogState) {
        _dialogMessage.update { model }
    }
}