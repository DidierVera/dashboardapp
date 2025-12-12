package com.came.parkare.dashboardapp.ui.components.carcounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CarCounterViewModel(
    private val carCounterManager: CarCounterManager
): ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int>
        get() = _counter.asStateFlow()

    private val _showCounter = MutableStateFlow(false)
    val showCounter: StateFlow<Boolean>
        get() = _showCounter.asStateFlow()

    init {
        showCarCounter()
        checkCounter()
    }

    private fun checkCounter() {
        carCounterManager.counter.onEach { value ->
            _counter.update { value }
        }.launchIn(viewModelScope)
    }

    private fun showCarCounter(){
        carCounterManager.showCounter.onEach { value ->
            _showCounter.update { value }
        }.launchIn(viewModelScope)
    }
}