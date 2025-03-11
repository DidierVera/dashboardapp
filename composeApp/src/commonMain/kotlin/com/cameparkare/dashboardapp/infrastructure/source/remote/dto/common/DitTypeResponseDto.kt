package com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DitTypeResponseDto(
    @SerialName("DitType")
    val ditType: Int,
    @SerialName("DitName")
    val ditName: String
)
