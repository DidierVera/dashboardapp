package com.cameparkare.dashboardapp.infrastructure.source.external.dto

import kotlinx.serialization.SerialName

data class FtpServerConnectionDto(
    @SerialName("device-root")
    val deviceRoot: FtpConnectionDto,
    @SerialName("app-root")
    val appRoot: FtpConnectionDto
)

data class FtpConnectionDto (
    val password: String,
    val port: Int,
    val username: String
)