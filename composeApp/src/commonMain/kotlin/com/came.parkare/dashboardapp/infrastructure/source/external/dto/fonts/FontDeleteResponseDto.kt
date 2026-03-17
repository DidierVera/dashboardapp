package com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts

import kotlinx.serialization.Serializable

@Serializable
data class FontDeleteResponseDto(
    val status: Boolean,
    val fileName: String,
    val message: String
)
