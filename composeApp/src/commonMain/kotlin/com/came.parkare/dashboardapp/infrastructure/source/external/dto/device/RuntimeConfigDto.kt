package com.came.parkare.dashboardapp.infrastructure.source.external.dto.device

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Ports where the servers of the dashboard are really listening (after validation and fallback),
 * served by the web configurator server at [com.came.parkare.dashboardapp.config.constants.ApiRequestUri.RUNTIME_CONFIG].
 */
@Serializable
data class RuntimeConfigDto(
    @SerialName("api-port") val apiPort: Int,
    @SerialName("web-port") val webPort: Int
)
