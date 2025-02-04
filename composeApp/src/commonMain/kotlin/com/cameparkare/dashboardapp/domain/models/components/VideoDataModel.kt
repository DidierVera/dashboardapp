package com.cameparkare.dashboardapp.domain.models.components

data class VideoDataModel(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val fileName: String? = null,
    val folderPath: String? = null,
    val style: CommonStyleModel
)
