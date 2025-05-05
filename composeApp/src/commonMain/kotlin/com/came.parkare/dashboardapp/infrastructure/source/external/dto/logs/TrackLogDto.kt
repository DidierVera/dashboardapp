package com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs

import kotlinx.serialization.Serializable

@Serializable
data class TrackLogDto(
    val tag: String = "",
    val message: String = ""
)
