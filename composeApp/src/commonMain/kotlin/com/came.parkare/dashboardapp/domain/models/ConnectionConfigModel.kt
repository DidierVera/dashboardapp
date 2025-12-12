package com.came.parkare.dashboardapp.domain.models

import kotlinx.serialization.SerialName

data class ConnectionConfigModel(
    val connectionWay: Int,
    val terminalIp: String,
    val port: Int,
    val apiPort: Int,
    val terminalApi: String?,
    val timeDelay: Int,
    val videoFrame: Boolean,
    val textSizeScale: Int,
    val autoBrightness: Boolean,
    val activeLowBrightnessTime: Int,
    val showCarCounter: Boolean,
    val carCounterReset: Int,
    val files: List<ImagesFileModel>? = null
)
