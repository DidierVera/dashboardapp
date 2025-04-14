package com.came.parkare.dashboardapp.ui.utils

import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.StringResource

interface WasmUtilsHandler {
    val loadingStatus: StateFlow<Boolean>
    val toastMessage: StateFlow<String>
    val toastResMessage: StateFlow<StringResource?>

    fun showToastMessage(message: String)
    fun showToastMessage(stringRes: StringResource)
    fun showLoading(value: Boolean)
}