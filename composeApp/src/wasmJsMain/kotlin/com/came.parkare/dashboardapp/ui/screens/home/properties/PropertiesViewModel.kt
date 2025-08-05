package com.came.parkare.dashboardapp.ui.screens.home.properties

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PropertiesViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(PropertiesState())
    val state: StateFlow<PropertiesState>
        get() = _state.asStateFlow()



}