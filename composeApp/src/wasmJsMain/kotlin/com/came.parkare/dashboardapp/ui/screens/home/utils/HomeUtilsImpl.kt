package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeUtilsImpl(
): HomeUtils {
    private val _isShowingProperties: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isShowingElements: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _shoBlankElements: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _showDefaultScreens: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _displayControls: MutableStateFlow<Boolean> = MutableStateFlow(false)


    override val isShowingProperties: StateFlow<Boolean>
        get() = _isShowingProperties.asStateFlow()

    override val isShowingElements: StateFlow<Boolean>
        get() = _isShowingElements.asStateFlow()

    override val blankElements: StateFlow<Boolean>
        get() = _shoBlankElements.asStateFlow()

    override val defaultScreens: StateFlow<Boolean>
        get() = _showDefaultScreens.asStateFlow()

    override val displayControls: StateFlow<Boolean>
        get() = _displayControls.asStateFlow()


    override fun showElements(value: Boolean) {
        hideTabs()
        _isShowingElements.update { value }
    }

    override fun showProperties(value: Boolean) {
        hideTabs()
        _isShowingProperties.update { value }
    }

    override fun showBlankElements(value: Boolean) {
        hideTabs()
        _shoBlankElements.update { value }
    }

    override fun showDefaultScreens(value: Boolean) {
        hideTabs()
        _showDefaultScreens.update { value }
    }

    override fun hideAllTabs() {
        hideTabs()
    }

    private fun hideTabs(){
        _shoBlankElements.update { false }
        _showDefaultScreens.update { false }
        _isShowingElements.update { false }
        _isShowingProperties.update { false }
        _displayControls.update { false }
    }
}