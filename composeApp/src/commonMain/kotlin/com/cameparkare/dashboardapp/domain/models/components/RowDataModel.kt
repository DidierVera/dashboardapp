package com.cameparkare.dashboardapp.domain.models.components

data class RowDataModel(
    val style: CommonStyleModel,
    val spacing: Int,
    val content: List<ElementModel>
)
