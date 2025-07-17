package com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs

import kotlinx.serialization.Serializable

@Serializable
data class TrackErrorDto(
    val localizedMessage: String,
    val stackTrace: String
)
