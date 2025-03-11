package com.cameparkare.dashboardapp.domain.models

import com.cameparkare.dashboardapp.domain.models.components.ElementModel

data class ScreenModel(
    val dispatcherCode: Long,
    val screenId: String,
    val elements: List<ElementModel>
)
