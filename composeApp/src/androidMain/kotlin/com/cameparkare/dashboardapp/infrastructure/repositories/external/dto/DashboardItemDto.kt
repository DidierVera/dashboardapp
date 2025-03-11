package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardItemDto(
    @SerialName("signalR-url") val signalRUrl: String?,
    @SerialName("socket-ip") val socketIp: String?,
    @SerialName("socket-port") val socketPort: Int?,
    @SerialName("time-delay") val timeDelay: Int,
    @SerialName("video-frame") val videoFrame: Boolean,
    @SerialName("text-size-scale") val textSizeScale: Int,
    @SerialName("margin-top") val marginTop: Int,
    @SerialName("margin-bottom") val marginBottom: Int,
    @SerialName("margin-left") val marginLeft: Int,
    @SerialName("margin-right") val marginRight: Int,
    val screens: List<ScreenDto>
)
