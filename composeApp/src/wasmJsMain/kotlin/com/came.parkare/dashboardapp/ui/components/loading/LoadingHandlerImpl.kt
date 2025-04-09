package com.came.parkare.dashboardapp.ui.components.loading

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoadingHandlerImpl: LoadingHandler {
    private val _loadingStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val loadingStatus: StateFlow<Boolean>
        get() = _loadingStatus.asStateFlow()

    override fun showLoading(value: Boolean) {
        _loadingStatus.update { value }
    }
}