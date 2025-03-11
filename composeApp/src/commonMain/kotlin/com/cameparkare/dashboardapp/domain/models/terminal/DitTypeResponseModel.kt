package com.cameparkare.dashboardapp.domain.models.terminal

import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common.DitTypeResponseDto


data class DitTypeResponseModel(
    val ditTypeCode: Int,
    val ditName: String
)

fun DitTypeResponseDto.toModel(): DitTypeResponseModel {
    return DitTypeResponseModel(
        ditTypeCode = ditType, ditName = ditName
    )
}
