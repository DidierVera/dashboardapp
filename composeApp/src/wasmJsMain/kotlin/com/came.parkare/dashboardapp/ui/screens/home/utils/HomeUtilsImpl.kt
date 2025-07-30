package com.came.parkare.dashboardapp.ui.screens.home.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeUtilsImpl(
): HomeUtils {
    private val _isShowingProperties: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isShowingElements: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val isShowingProperties: StateFlow<Boolean>
        get() = _isShowingProperties.asStateFlow()

    override val isShowingElements: StateFlow<Boolean>
        get() = _isShowingElements.asStateFlow()

    override fun showElements(value: Boolean) {
        _isShowingElements.update { value }
    }

    override fun showProperties(value: Boolean) {
        _isShowingProperties.update { value }
    }
}