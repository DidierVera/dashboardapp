package com.came.parkare.dashboardapp.domain.models.components

data class BoxDataModel(
    val style: CommonStyleModel,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val content: List<ElementModel>
)
