package com.came.parkare.dashboardapp.domain.models

data class FontInfo(
    val fileName: String,
    val size: Long,
    val lastModified: Long,
    val isValid: Boolean
)