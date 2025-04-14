package com.came.parkare.dashboardapp.ui.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WasmUtilsHandlerImpl: WasmUtilsHandler {
    private val _loadingStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _toastMessage: MutableStateFlow<String> = MutableStateFlow("")

    override val loadingStatus: StateFlow<Boolean>
        get() = _loadingStatus.asStateFlow()

    override val toastMessage: StateFlow<String>
        get() = _toastMessage.asStateFlow()

    override fun showToastMessage(message: String) {
        _toastMessage.update { message }
    }

    override fun showLoading(value: Boolean) {
        _loadingStatus.update { value }
    }
}