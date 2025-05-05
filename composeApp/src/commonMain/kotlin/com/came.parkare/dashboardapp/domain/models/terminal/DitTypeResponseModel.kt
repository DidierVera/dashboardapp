package com.came.parkare.dashboardapp.domain.models.terminal

import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DitTypeResponseDto


data class DitTypeResponseModel(
    val ditTypeCode: Int,
    val ditName: String
)

fun DitTypeResponseDto.toModel(): DitTypeResponseModel {
    return DitTypeResponseModel(
        ditTypeCode = ditType, ditName = ditName
    )
}
