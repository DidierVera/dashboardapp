package com.came.parkare.dashboardapp.ui.interfaces

interface ResourceController {
    fun getString(resourceId: Int): String
    fun getDeviceIpAddress(): String
}