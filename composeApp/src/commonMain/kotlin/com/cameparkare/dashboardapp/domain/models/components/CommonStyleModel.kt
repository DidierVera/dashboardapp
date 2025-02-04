package com.cameparkare.dashboardapp.domain.models.components

data class CommonStyleModel(
    val backgroundColor: String? = null,
    val density: Int = 100,
    val roundBorder: Int = 0,
    val hasShadow: Boolean = false,
    val padding: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val margin: Int?  = null
)
