package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardItemDto(
    val screens: List<ScreenDto>
)
