package com.came.parkare.dashboardapp.domain.models.terminal

import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DitResponseDto
import kotlinx.serialization.json.JsonObject

data class DitResponseModel(
    val ditType: DitTypeResponseModel,
    val version: Int,
    val additionalProperties: JsonObject?
)


fun DitResponseDto.toModel(): DitResponseModel {
    return DitResponseModel(
        version = version,
        additionalProperties = additionalProperties,
        ditType = ditType.toModel()
    )
}