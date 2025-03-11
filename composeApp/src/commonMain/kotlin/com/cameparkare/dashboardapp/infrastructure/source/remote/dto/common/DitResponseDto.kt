package com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonObject


data class DitResponseDto(
    @SerialName("DitType") val ditType: DitTypeResponseDto,
    @SerialName("Version") val version: Int,
    val additionalProperties: JsonObject?
)
