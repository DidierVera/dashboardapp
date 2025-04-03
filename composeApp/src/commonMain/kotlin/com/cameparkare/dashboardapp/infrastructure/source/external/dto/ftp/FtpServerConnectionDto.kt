package com.cameparkare.dashboardapp.infrastructure.source.external.dto.ftp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FtpServerConnectionDto(
    @SerialName("device-root")
    val deviceRoot: FtpConnectionDto,
    @SerialName("app-root")
    val appRoot: FtpConnectionDto
)

@Serializable
data class FtpConnectionDto (
    val password: String,
    val port: Int,
    val username: String
)