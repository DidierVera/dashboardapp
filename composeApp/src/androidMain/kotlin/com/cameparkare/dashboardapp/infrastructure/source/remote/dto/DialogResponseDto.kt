package com.cameparkare.dashboardapp.infrastructure.source.remote.dto

import com.google.gson.annotations.SerializedName


data class DialogResponseDto(
    @SerializedName("DialogName")
    val dialogName: String,
    @SerializedName("DialogNr")
    val dialogNumber: Int
)
