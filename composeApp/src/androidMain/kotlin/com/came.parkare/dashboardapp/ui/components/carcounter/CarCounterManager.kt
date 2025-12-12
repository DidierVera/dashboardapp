package com.came.parkare.dashboardapp.ui.components.carcounter

import kotlinx.coroutines.flow.StateFlow

interface CarCounterManager {
    fun setCounter(counter: Int)
    fun showCarCounter(show: Boolean)
    fun setResetDelay(delay: Int)

    val counter: StateFlow<Int>
    val showCounter: StateFlow<Boolean>
    val resetDelay: StateFlow<Int>
}