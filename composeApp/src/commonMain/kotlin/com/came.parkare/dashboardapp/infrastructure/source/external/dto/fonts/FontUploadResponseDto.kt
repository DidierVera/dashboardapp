package com.came.parkare.dashboardapp.infrastructure.source.external.dto.fonts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FontUploadResponseDto(
    @SerialName("status") val status: Boolean,
    @SerialName("file-name") val fileName: String? = null,
    @SerialName("size") val size: Long? = null,
    @SerialName("message") val message: String? = null
)
