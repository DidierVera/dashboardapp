package com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FontInfoDto(
    @SerialName("file-name") val fileName: String,
    @SerialName("size") val size: Long,
    @SerialName("last-modified") val lastModified: Long,
    @SerialName("is-valid") val isValid: Boolean
)