package com.came.parkare.dashboardapp.ui.screens.home.properties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class PropertiesViewModel(
    private val resourceUtils: ResourceUtils

): ViewModel() {
    private val _state = MutableStateFlow(PropertiesState())
    val state: StateFlow<PropertiesState>
        get() = _state.asStateFlow()

    init {
        resourceUtils.editingElement.onEach { jsonElement ->
            val newElement = Json.decodeFromString<ElementDto>(jsonElement)
            _state.update { it.copy(element = newElement) }

        }.launchIn(viewModelScope)
    }


}