package com.came.parkare.dashboardapp.ui.screens.home.blankscreen

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BlankScreenViewModel(
    private val homeUtils: HomeUtils,
    private val resourceUtils: ResourceUtils
): ViewModel() {
    private val _state = MutableStateFlow(BlankScreenState())
    val state: StateFlow<BlankScreenState> = _state.asStateFlow()

    fun onDispatcherCode(value: String) {
        _state.update { it.copy(dispatcherCode = value) }
    }

    fun onScreenId(value: String) {
        _state.update { it.copy(screenId = value) }
    }

    fun onMarginTop(value: String) {
        _state.update { it.copy(marginTop = value) }
    }

    fun onMarginBottom(value: String) {
        _state.update { it.copy(marginBottom = value) }
    }

    fun onMarginLeft(value: String) {
        _state.update { it.copy(marginLeft = value) }
    }

    fun onMarginRight(value: String) {
        _state.update { it.copy(marginRight = value) }
    }

    fun hide() {
        homeUtils.showBlankScreen(false)

    }

    fun save() {
        val screen = ScreenModel(
            dispatcherCode = _state.value.dispatcherCode.toLongOrNull() ?: 0L,
            screenId = _state.value.screenId,
            marginTop = _state.value.marginTop.toIntOrNull() ?: 0,
            marginBottom = _state.value.marginBottom.toIntOrNull() ?: 0,
            marginLeft = _state.value.marginLeft.toIntOrNull() ?: 0,
            marginRight = _state.value.marginRight.toIntOrNull() ?: 0,
            elements = emptyList()
        )
        val currentTemplate = resourceUtils.editableTemplate.value
        val newScreens = mutableListOf<ScreenModel>()
        newScreens.addAll(currentTemplate.screens)
        newScreens.add(screen)
        resourceUtils.setEditableTemplate(currentTemplate.copy(screens = newScreens))
        hide()
        clearForm()
    }
    private fun clearForm() {
        _state.update { BlankScreenState() }
    }
}
