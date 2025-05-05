package com.came.parkare.dashboardapp.domain.models.components

data class VideoDataModel(
    val dashboardItemId: String,
    val dataKey: String? = null,
    val ditTypeCode: Int? = null,
    val validValue: Int? = null,
    val fileName: String? = null,
    val folderPath: String? = null,
    val style: CommonStyleModel
)
