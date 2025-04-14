package com.came.parkare.dashboardapp.ui.utils

import kotlinx.coroutines.flow.StateFlow

interface WasmUtilsHandler {
    val loadingStatus: StateFlow<Boolean>
    val toastMessage: StateFlow<String>

    fun showToastMessage(message: String)
    fun showLoading(value: Boolean)
}