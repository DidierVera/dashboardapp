package com.came.parkare.dashboardapp.ui.screens.home.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeUtilsImpl(
): HomeUtils {
    private val _isShowingProperties: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _isShowingDefaultElements: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isShowingBlankElements: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isShowingDefaultScreens: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isShowingBlankScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _displayControls: MutableStateFlow<Boolean> = MutableStateFlow(false)


    override val isShowingProperties: StateFlow<Boolean>
        get() = _isShowingProperties.asStateFlow()

    override val isShowingDefaultElements: StateFlow<Boolean>
        get() = _isShowingDefaultElements.asStateFlow()

    override val isShowingBlankElements: StateFlow<Boolean>
        get() = _isShowingBlankElements.asStateFlow()

    override val blankScreen: StateFlow<Boolean>
        get() = _isShowingBlankScreen.asStateFlow()

    override val isShowingDefaultScreens: StateFlow<Boolean>
        get() = _isShowingDefaultScreens.asStateFlow()

    override val displayControls: StateFlow<Boolean>
        get() = _displayControls.asStateFlow()


    override fun showElements(value: Boolean) {
        hideTabs()
        _isShowingDefaultElements.update { value }
    }

    override fun showProperties(value: Boolean) {
        hideTabs()
        _isShowingProperties.update { value }
    }

    override fun showBlankElements(value: Boolean) {
        hideTabs()
        _isShowingBlankElements.update { value }
    }
    override fun showBlankScreen(value: Boolean) {
        hideTabs()
        _isShowingBlankScreen.update { value }
    }

    override fun showDefaultScreens(value: Boolean) {
        hideTabs()
        _isShowingDefaultScreens.update { value }
    }

    override fun hideAllTabs() {
        hideTabs()
    }

    private fun hideTabs(){
        _isShowingBlankElements.update { false }
        _isShowingDefaultScreens.update { false }
        _isShowingDefaultElements.update { false }
        _isShowingProperties.update { false }
        _displayControls.update { false }
        _isShowingBlankScreen.update { false }
    }
}