package com.cameparkare.dashboardapp.infrastructure.source.remote.dto

import com.google.gson.annotations.SerializedName


data class TypeResponseDto(
    @SerializedName("DtoName")
    val dtoName: String,
    @SerializedName("DtoType")
    val dtoType: Int
)
