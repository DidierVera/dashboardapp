package com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeResponseDto(
    @SerialName("DtoName")
    val dtoName: String,
    @SerialName("DtoType")
    val dtoType: Int
)
