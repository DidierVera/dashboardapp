package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import com.google.gson.annotations.SerializedName

data class DashboardItemDto(
    val screens: List<ScreenDto>,
    @SerializedName("socket-ip")
    val socketIp: String?,
    @SerializedName("socket-port")
    val socketPort: String?,
    @SerializedName("signalR-url")
    val signalRUrl: String?,
    @SerializedName("time-delay")
    val timeDelay: Int?,
    @SerializedName("video-frame")
    val videoFrame: Boolean?,
    @SerializedName("text-size-scale")
    val textSizeScale: Int?,
    @SerializedName("margin-top")
    val marginTop: Int?,
    @SerializedName("margin-bottom")
    val marginBottom: Int?,
    @SerializedName("margin-left")
    val marginLeft: Int?,
    @SerializedName("margin-right")
    val marginRight: Int?
)
