package com.came.parkare.dashboardapp.ui.components.carcounter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CarCounterManagerImpl(): CarCounterManager {

    private val _counter = MutableStateFlow(0)
    private val _showCounter = MutableStateFlow(false)
    private val _resetDelay = MutableStateFlow(0)

    override fun setCounter(counter: Int) {
        _counter.update { counter }
    }

    override fun showCarCounter(show: Boolean) {
        _showCounter.update { show }
    }

    override fun setResetDelay(delay: Int) {
        _resetDelay.update { delay }
    }

    override val counter: StateFlow<Int>
        get() = _counter.asStateFlow()
    override val showCounter: StateFlow<Boolean>
        get() = _showCounter.asStateFlow()
    override val resetDelay: StateFlow<Int>
        get() = _resetDelay.asStateFlow()
}