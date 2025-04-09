package com.came.parkare.dashboardapp.ui.components.loading

import kotlinx.coroutines.flow.StateFlow

interface LoadingHandler {
    val loadingStatus: StateFlow<Boolean>

    fun showLoading(value: Boolean)
}