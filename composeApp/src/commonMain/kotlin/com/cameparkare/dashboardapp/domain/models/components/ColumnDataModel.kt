package com.cameparkare.dashboardapp.domain.models.components

data class ColumnDataModel (
    val style: CommonStyleModel,
    val spacing: Int,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val content: List<ElementModel>
)