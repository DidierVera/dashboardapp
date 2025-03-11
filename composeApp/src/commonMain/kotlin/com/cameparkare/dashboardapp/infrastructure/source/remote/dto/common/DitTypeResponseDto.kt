package com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName

data class DitTypeResponseDto(
    @SerialName("DitType")
    val ditType: Int,
    @SerialName("DitName")
    val ditName: String
)
