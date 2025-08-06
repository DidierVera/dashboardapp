package com.came.parkare.dashboardapp.ui.screens.home.elementlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.mocks.ElementListMock
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class ElementListViewModel(
    private val homeUtils: HomeUtils

): ViewModel() {

    private val _state = MutableStateFlow(ElementListState())
    val state: StateFlow<ElementListState>
        get() = _state.asStateFlow()

    init {
        loadBlankItems()
        loadDefaultElements()
        checkDisplayBlankTab()
        checkDisplayDefaultTab()
        loadImages()
    }

    private fun loadImages() {
        homeUtils.imagesSource.onEach { images ->
            _state.update { it.copy(imagesSource = images) }
        }.launchIn(viewModelScope)
        homeUtils.textSizeScale.onEach { size ->
            _state.update { it.copy(textSizeScale = size) }
        }.launchIn(viewModelScope)
    }

    private fun loadDefaultElements() {
        try {
            val itemsJson = ElementListMock.getDefaultElements()
            val newItems  = Json.decodeFromString<List<ElementDto>>(itemsJson)
            _state.update { it.copy(defaultItems = newItems) }
        }catch (e: Exception){
            e.printStackTrace()
            println(e)
        }
    }

    private fun checkDisplayDefaultTab() {
        homeUtils.isShowingElements.onEach { display ->
            _state.update { it.copy(showDefaultTab = display) }
        }.launchIn(viewModelScope)
    }

    private fun checkDisplayBlankTab() {
        homeUtils.blankElements.onEach { display ->
            _state.update { it.copy(showBlankTab = display) }
        }.launchIn(viewModelScope)
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