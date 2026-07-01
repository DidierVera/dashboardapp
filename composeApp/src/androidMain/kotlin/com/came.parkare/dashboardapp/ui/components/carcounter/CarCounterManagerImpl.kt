package com.came.parkare.dashboardapp.ui.components.carcounter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CarCounterManagerImpl(): CarCounterManager {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private var resetJob: Job? = null

    private val _counter = MutableStateFlow(0)
    private val _showCounter = MutableStateFlow(false)
    private val _resetDelay = MutableStateFlow(0)

    override fun newCarEntered() {
        _counter.update { it+1 }
    }

    override fun resetCarCounter() {
        _counter.update { 0 }
    }

    override fun showCarCounter(show: Boolean) {
        _showCounter.update { show }
        resetCarCounter()
        startResetTimer()
    }

    override fun setResetDelay(delay: Int) {
        _resetDelay.update { delay }
        resetCarCounter()
        startResetTimer()
    }

    override val counter: StateFlow<Int>
        get() = _counter.asStateFlow()
    override val showCounter: StateFlow<Boolean>
        get() = _showCounter.asStateFlow()
    override val resetDelay: StateFlow<Int>
        get() = _resetDelay.asStateFlow()


    private fun startResetTimer() {
        val showCounter = _showCounter.value
        if (showCounter){
            coroutineScope.launch {
                _resetDelay.collect { delay ->
                    restartTimerWithDelay(delay)
                }
            }
        }
    }

    private fun restartTimerWithDelay(delay: Int) {
        // Cancel existing timer
        resetJob?.cancel()

        if (delay <= 0) return

        // Start new periodic timer
        resetJob = coroutineScope.launch {
            while (isActive) {
                delay(delay.toLong() * 60 * 1000L)
                resetCarCounter()
            }
        }
    }

    fun cleanup() {
        resetJob?.cancel()
        coroutineScope.cancel()
    }
}