package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConnectionConfigDto(
    @SerialName("connection-way") val connectionWay: Int,
    @SerialName("terminal-ip") val terminalIp: String,
    @SerialName("port") val port: Int,
    @SerialName("api") val terminalApi: String?,
    @SerialName("time-delay") val timeDelay: Int,
    @SerialName("video-frame") val videoFrame: Boolean,
    @SerialName("text-size-scale") val textSizeScale: Int,
    @SerialName("files") val files: Map<String, String>? = null
)