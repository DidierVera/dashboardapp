package com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common

import kotlinx.serialization.SerialName

data class TypeResponseDto(
    @SerialName("DtoName")
    val dtoName: String,
    @SerialName("DtoType")
    val dtoType: Int
)
