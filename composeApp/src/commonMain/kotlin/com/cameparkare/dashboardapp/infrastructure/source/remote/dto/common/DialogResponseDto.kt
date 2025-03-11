package com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DialogResponseDto(
    @SerialName("DialogName")
    val dialogName: String,
    @SerialName("DialogNr")
    val dialogNumber: Int
)