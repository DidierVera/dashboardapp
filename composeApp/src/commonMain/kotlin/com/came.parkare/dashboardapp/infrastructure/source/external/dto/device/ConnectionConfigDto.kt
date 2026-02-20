package com.came.parkare.dashboardapp.infrastructure.source.external.dto.device

import com.came.parkare.dashboardapp.domain.models.ConnectionConfigModel
import com.came.parkare.dashboardapp.domain.models.toDto
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
    @SerialName("auto-brightness-mode") val autoBrightness: Boolean? = false,
    @SerialName("active-low-brightness-time") val activeLowBrightnessTime: Int? = 2,
    @SerialName("files") val files: List<ImageFileDto>? = null
)

fun ConnectionConfigDto.toModel(): ConnectionConfigModel {
    return ConnectionConfigModel(
        connectionWay=connectionWay,
        files = files?.map { it.toModel() },
        terminalIp=terminalIp, port=port, apiPort=apiPort, terminalApi=terminalApi,
        timeDelay=timeDelay, videoFrame=videoFrame, textSizeScale=textSizeScale,
        autoBrightness = autoBrightness == true, activeLowBrightnessTime = activeLowBrightnessTime ?: 2
    )
}

fun ConnectionConfigModel.toDto(): ConnectionConfigDto {
    return ConnectionConfigDto(
        connectionWay=connectionWay,
        files = files?.map { it.toDto() },
        terminalIp=terminalIp, port=port, apiPort=apiPort, terminalApi=terminalApi,
        timeDelay=timeDelay, videoFrame=videoFrame, textSizeScale=textSizeScale,
        autoBrightness = autoBrightness, activeLowBrightnessTime = activeLowBrightnessTime
    )
}