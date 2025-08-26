package com.came.parkare.dashboardapp.ui.screens.home.properties.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.screens.home.properties.PropertiesState
import com.came.parkare.dashboardapp.ui.screens.home.utils.HomeUtils
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class PropertiesViewModel(
    private val resourceUtils: ResourceUtils,
    private val homeUtils: HomeUtils

): ViewModel() {
    private val _state = MutableStateFlow(PropertiesState())
    val state: StateFlow<PropertiesState>
        get() = _state.asStateFlow()

    fun initTab() {
        homeUtils.isShowingProperties.onEach { show ->
            _state.update { it.copy(showTab = show) }
        }.launchIn(viewModelScope)
        resourceUtils.editingElement.onEach { jsonElement ->
            if (jsonElement.isNotEmpty()){
                val newElement = try {
                    Json.decodeFromString<ElementDto>(jsonElement)
                }catch (e: Exception){
                    e.printStackTrace()
                    null
                }
                if (newElement != null){
                    _state.update { it.copy(element = newElement) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setNewValues(){

    }
}