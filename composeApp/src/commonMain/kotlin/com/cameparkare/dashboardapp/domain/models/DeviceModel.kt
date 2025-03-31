package com.cameparkare.dashboardapp.domain.models

data class DeviceModel(
    val id: Long = 0,
    val deviceIp: String,
    val customName: String,
    val terminalIp: String? = null
)
