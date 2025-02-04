package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto

import kotlinx.serialization.SerialName


data class ScreenDto (
    @SerialName("dispatch-code")
    val dispatchCode: Int,
    val data: List<ElementDto>,
    @SerialName("screen-id")
    val screenId: String
)