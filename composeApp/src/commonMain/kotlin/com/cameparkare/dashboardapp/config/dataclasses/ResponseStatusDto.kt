package com.cameparkare.dashboardapp.config.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class ResponseStatusDto(
    val status: Boolean
)
