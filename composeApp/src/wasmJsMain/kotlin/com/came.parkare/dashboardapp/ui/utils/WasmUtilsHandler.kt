package com.came.parkare.dashboardapp.ui.utils

import kotlinx.coroutines.flow.StateFlow

interface WasmUtilsHandler {
    val loadingStatus: StateFlow<Boolean>

    fun showLoading(value: Boolean)
}