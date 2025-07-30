package com.came.parkare.dashboardapp.ui.screens.home.elementlist

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementSerializer
import com.came.parkare.dashboardapp.infrastructure.source.mocks.ElementListMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class ElementListViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(ElementListState())
    val state: StateFlow<ElementListState>
        get() = _state.asStateFlow()

    init {
        loadBlankItems()
    }

    private fun loadBlankItems() {
        try {
            val itemsJson = ElementListMock.getElementList()
            val newItems  = Json.decodeFromString<List<ElementDto>>(itemsJson)
            _state.update { it.copy(blankElements = newItems) }
        }catch (e: Exception){
            e.printStackTrace()
            println(e)
        }
    }
}