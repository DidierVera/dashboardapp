package com.cameparkare.dashboardapp.domain.models.Components

data class RowDataModel(
    val style: CommonStyleModel,
    val spacing: Int,
    val content: List<ElementModel>
)
