package com.cameparkare.dashboardapp.infrastructure.source.remote.dto

import kotlinx.serialization.SerialName

data class DialogResponseDto(
    @SerialName("DialogName")
    val dialogName: String,
    @SerialName("DialogNr")
    val dialogNumber: Int
)
