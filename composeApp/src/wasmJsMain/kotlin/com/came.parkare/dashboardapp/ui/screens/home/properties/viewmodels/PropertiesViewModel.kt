package com.came.parkare.dashboardapp.ui.screens.home.properties.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.getElementsDtoToModel
import com.came.parkare.dashboardapp.ui.components.properties.toPrettyJson
import com.came.parkare.dashboardapp.ui.screens.home.properties.PropertiesState
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class PropertiesViewModel(
    private val resourceUtils: ResourceUtils,
    private val homeUtils: HomeUtils

): ViewModel() {
    private val _state = MutableStateFlow(PropertiesState())
    val state: StateFlow<PropertiesState>
        get() = _state.asStateFlow()

    init {
        homeUtils.isShowingProperties.onEach { show ->
            _state.update { it.copy(showTab = show) }
        }.launchIn(viewModelScope)
        resourceUtils.editingElement.onEach { jsonElement ->
            _state.update { it.copy(elementJsonCode = jsonElement, location = resourceUtils.editingLocation.value) }
        }.launchIn(viewModelScope)
    }

    fun setJsonCode(jsonCode: String) {
        _state.update { it.copy(elementJsonCode = jsonCode) }
    }

    /** Writes the edited element back to its screen of the editable template. */
    fun setNewValues(element: ElementDto) {
        val location = _state.value.location
        resourceUtils.setEditingElement(element.toPrettyJson(), location)
        if (location == null) return

        val newModel = getElementsDtoToModel(listOf(element)).firstOrNull() ?: return
        val template = resourceUtils.editableTemplate.value
        val newScreens = template.screens.map { screen ->
            if (screen.screenId == location.screenId && location.position in screen.elements.indices) {
                screen.copy(elements = screen.elements.toMutableList().apply { this[location.position] = newModel })
            } else screen
        }
        resourceUtils.setEditableTemplate(template.copy(screens = newScreens), resetEditingElement = false)
    }
}
