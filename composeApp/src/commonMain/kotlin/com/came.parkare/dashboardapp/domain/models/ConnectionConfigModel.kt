package com.came.parkare.dashboardapp.domain.models

data class ConnectionConfigModel(
    val connectionWay: Int,
    val terminalIp: String,
    val port: Int,
    val apiPort: Int,
    val terminalApi: String?,
    val timeDelay: Int,
    val videoFrame: Boolean,
    val textSizeScale: Int,
    val files: Map<String, String>? = null
)
