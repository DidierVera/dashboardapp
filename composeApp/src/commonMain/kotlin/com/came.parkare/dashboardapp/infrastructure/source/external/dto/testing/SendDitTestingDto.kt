package com.came.parkare.dashboardapp.infrastructure.source.external.dto.testing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendDitTestingDto(
    @SerialName("dispatch-code")
    val dispatchCode: Long,
    @SerialName("screen-id")
    val screenId: String,
    val dits: List<DitTestingDto>,
)

@Serializable
data class DitTestingDto(
    @SerialName("dit-type-code")
    val ditTypeCode: Int,
    @SerialName("dit-name")
    val ditName: String,
    val fields: Map<String, String>,
)
