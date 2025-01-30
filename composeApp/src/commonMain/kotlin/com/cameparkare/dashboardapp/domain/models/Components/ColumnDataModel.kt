package com.cameparkare.dashboardapp.domain.models.Components

data class ColumnDataModel (
    val style: CommonStyleModel,
    val spacing: Int,
    val content: List<ElementModel>
)