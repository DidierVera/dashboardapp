package com.came.parkare.dashboardapp.domain.models.components

data class ImageDataModel(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val fileName: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val folderPath: String? = null,
    val interval: Int? = null,
    val style: CommonStyleModel
)
