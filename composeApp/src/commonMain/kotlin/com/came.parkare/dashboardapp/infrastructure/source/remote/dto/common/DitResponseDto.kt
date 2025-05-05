package com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class DitResponseDto(
    @SerialName("DitType") val ditType: DitTypeResponseDto,
    @SerialName("Version") val version: Int,
    val additionalProperties: JsonObject?
)
