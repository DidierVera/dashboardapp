package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import com.google.gson.annotations.SerializedName

data class ScreenDto (
    @SerializedName("dispatch-code")
    val dispatchCode: Int,
    val data: List<ElementDto>,
    @SerializedName("screen-id")
    val screenId: String
)