package com.cameparkare.dashboardapp.domain.models.components

data class ColumnDataModel (
    val style: CommonStyleModel,
    val spacing: Int,
    val content: List<ElementModel>
)