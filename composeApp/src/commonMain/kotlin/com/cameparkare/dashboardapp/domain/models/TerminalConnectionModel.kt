package com.cameparkare.dashboardapp.domain.models

data class TerminalConnectionModel(
    val connectionWay: Int,
    val terminalIp: String,
    val port: Int,
    val terminalApi: String?,
    val timeDelay: Int,
    val videoFrame: Boolean,
    val textSizeScale: Int,
    val files: Map<String, String>? = null
)
