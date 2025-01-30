package com.cameparkare.dashboardapp.domain.models.Components

data class TextDataModel(
    val dashboardItemId: String,
    val defaultText: String,
    val textSize: Int,
    val textColor: String,
    val style: CommonStyleModel,
    val fontWeight: String,
    val dataKey: String? = null,
    val translations: Map<String, String>? = null
)
