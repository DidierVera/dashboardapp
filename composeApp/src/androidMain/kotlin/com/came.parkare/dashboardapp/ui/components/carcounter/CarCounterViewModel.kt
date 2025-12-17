package com.came.parkare.dashboardapp.ui.components.carcounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CarCounterViewModel(
    private val carCounterManager: CarCounterManager
): ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int>
        get() = _counter.asStateFlow()

    private val _timer = MutableStateFlow(0)
    val timer: StateFlow<Int>
        get() = _timer.asStateFlow()

    private val _showCounter = MutableStateFlow(false)
    val showCounter: StateFlow<Boolean>
        get() = _showCounter.asStateFlow()

    private val _initialDate = MutableStateFlow(Date())
    val initialDate: StateFlow<Date>
        get() = _initialDate.asStateFlow()

    private val _finalDate = MutableStateFlow(Date())
    val finalDate: StateFlow<Date>
        get() = _finalDate.asStateFlow()

    init {
        showCarCounter()
        checkCounter()
        checkTimer()
    }

    private fun checkTimer() {
        carCounterManager.resetDelay.onEach { timerValue ->
            _timer.update { timerValue }
            updateFinalDateBasedOnInitial()
        }.launchIn(viewModelScope)
    }

    private fun checkCounter() {
        carCounterManager.counter.onEach { counterValue ->
            _counter.update { counterValue }

            if (counterValue == 0) {
                updateInitialDate()
            }
        }.launchIn(viewModelScope)
    }

    private fun showCarCounter(){
        carCounterManager.showCounter.onEach { value ->
            _showCounter.update { value }
        }.launchIn(viewModelScope)
    }

    private fun updateInitialDate() {
        val newInitialDate = Date()
        _initialDate.update { newInitialDate }
        updateFinalDateBasedOnInitial()
    }

    private fun updateFinalDateBasedOnInitial() {
        val currentInitialDate = _initialDate.value
        val timerMinutes = _timer.value

        val calendar = Calendar.getInstance()
        calendar.time = currentInitialDate
        calendar.add(Calendar.MINUTE, timerMinutes)

        _finalDate.update { calendar.time }
    }

    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return formatter.format(date)
    }

    fun logCurrentState() {
        println("Counter: ${_counter.value}, Timer: ${_timer.value} min")
        println("Initial: ${formatDate(_initialDate.value)}")
        println("Final: ${formatDate(_finalDate.value)}")
    }
}