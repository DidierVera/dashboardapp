package com.cameparkare.dashboardapp.domain.models.components

data class TextDataModel(
    val dashboardItemId: String,
    val defaultText: String,
    val textSize: Int,
    val textColor: String,
    val style: CommonStyleModel,
    val fontWeight: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val translations: Map<String, String>? = null
)
