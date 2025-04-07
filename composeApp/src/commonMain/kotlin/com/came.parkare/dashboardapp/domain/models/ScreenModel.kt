package com.came.parkare.dashboardapp.domain.models

import com.came.parkare.dashboardapp.domain.models.components.ElementModel

data class ScreenModel(
    val dispatcherCode: Long,
    val screenId: String,
    val marginTop: Int = 0,
    val marginBottom: Int = 0,
    val marginLeft: Int = 0,
    val marginRight: Int = 0,
    val elements: List<ElementModel>
)
