package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import kotlinx.serialization.SerialName


data class DashboardItemDto(
    val screens: List<ScreenDto>,
    @SerialName("socket-ip")
    val socketIp: String?,
    @SerialName("socket-port")
    val socketPort: String?,
    @SerialName("signalR-url")
    val signalRUrl: String?,
    @SerialName("time-delay")
    val timeDelay: Int?,
    @SerialName("video-frame")
    val videoFrame: Boolean?,
    @SerialName("text-size-scale")
    val textSizeScale: Int?,
    @SerialName("margin-top")
    val marginTop: Int?,
    @SerialName("margin-bottom")
    val marginBottom: Int?,
    @SerialName("margin-left")
    val marginLeft: Int?,
    @SerialName("margin-right")
    val marginRight: Int?
)
