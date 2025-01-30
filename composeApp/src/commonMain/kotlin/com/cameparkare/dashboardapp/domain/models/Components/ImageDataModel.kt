package com.cameparkare.dashboardapp.domain.models.Components

data class ImageDataModel(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val fileName: String? = null,
    val localFilePath: String? = null,
    val style: CommonStyleModel
)
