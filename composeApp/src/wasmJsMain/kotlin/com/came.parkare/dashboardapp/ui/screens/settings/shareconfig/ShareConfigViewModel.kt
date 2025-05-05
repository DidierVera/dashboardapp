package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShareConfigViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(ShareConfigState())
    val state: StateFlow<ShareConfigState>
        get() = _state.asStateFlow()

}