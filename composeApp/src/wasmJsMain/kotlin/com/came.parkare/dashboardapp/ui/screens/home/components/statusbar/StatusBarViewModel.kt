package com.came.parkare.dashboardapp.ui.screens.home.components.statusbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class StatusBarViewModel(
    private val resourceUtils: ResourceUtils
): ViewModel() {
    private val _state = MutableStateFlow(StatusBarState())
    val state: StateFlow<StatusBarState>
        get() = _state.asStateFlow()

    fun initStatusBar(){
        setTemplateName()
        setElementType()
    }

    private fun setTemplateName() {
        _state.update { it.copy(templateName = "Blank template") }
        resourceUtils.editableTemplate.onEach { template ->
            _state.update { it.copy(templateName = template.templateName) }
        }.launchIn(viewModelScope)
    }

    private fun setElementType() {
        resourceUtils.editingElement.onEach { jsonElement ->
            val elementType = when(val element = Json.decodeFromString<ElementDto>(jsonElement)){
                is ElementDto.BoxDto -> element.elementType
                is ElementDto.ColumnDto -> element.elementType
                is ElementDto.ImageDto -> element.elementType
                is ElementDto.RowDto -> element.elementType
                is ElementDto.SpacerDto -> element.elementType
                is ElementDto.TextDto -> element.elementType
                is ElementDto.VideoDto -> element.elementType
            }
            _state.update { it.copy(editingElementType = elementType) }
        }.launchIn(viewModelScope)
    }
}