package com.came.parkare.dashboardapp.ui.components.carcounter

import kotlinx.coroutines.flow.StateFlow

interface CarCounterManager {
    fun newCarEntered()
    fun showCarCounter(show: Boolean)
    fun setResetDelay(delay: Int)
    fun resetCarCounter()

    val counter: StateFlow<Int>
    val showCounter: StateFlow<Boolean>
    val resetDelay: StateFlow<Int>
}