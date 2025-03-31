package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device

import com.cameparkare.dashboardapp.domain.models.ConnectionConfigModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConnectionConfigDto(
    @SerialName("connection-way") val connectionWay: Int,
    @SerialName("terminal-ip") val terminalIp: String,
    @SerialName("port") val port: Int,
    @SerialName("api-port") val apiPort: Int,
    @SerialName("api") val terminalApi: String?,
    @SerialName("time-delay") val timeDelay: Int,
    @SerialName("video-frame") val videoFrame: Boolean,
    @SerialName("text-size-scale") val textSizeScale: Int,
    @SerialName("files") val files: Map<String, String>? = null
)

fun ConnectionConfigDto.toModel(): ConnectionConfigModel {
    return ConnectionConfigModel(
        connectionWay=connectionWay,
        terminalIp=terminalIp, port=port, apiPort=apiPort, terminalApi=terminalApi,
        timeDelay=timeDelay, videoFrame=videoFrame, textSizeScale=textSizeScale
    )
}

fun ConnectionConfigModel.toDto(): ConnectionConfigDto {
    return ConnectionConfigDto(
        connectionWay=connectionWay,
        terminalIp=terminalIp, port=port, apiPort=apiPort, terminalApi=terminalApi,
        timeDelay=timeDelay, videoFrame=videoFrame, textSizeScale=textSizeScale
    )
}