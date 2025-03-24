package com.cameparkare.dashboardapp.ui.theme

import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val BlackColor = Color(0xFF000000)
val CameBlueColor = Color(0xFF009FE3)
val LightBlackColor = Color(0xFF282858)
val GreenColor = Color(0xFF35C759)
val YellowColor = Color(0xFFF2F208)
val WarningColor = Color(0xFFF08200)
val WhiteColor = Color(0xFFFFFFFF)
val LicenseBlueColor = Color(0xFF033F9D)
val HeaderColor = Color(0xFF2E294E)

fun hexToColor(hex: String?): Color {
    if (hex.isNullOrEmpty()) throw IllegalArgumentException("Invalid hex color format")
    val cleanedHex = hex.removePrefix("#")
    return when (cleanedHex.length) {
        6 -> Color(
            red = cleanedHex.substring(0, 2).toInt(16),
            green = cleanedHex.substring(2, 4).toInt(16),
            blue = cleanedHex.substring(4, 6).toInt(16),
            alpha = 255
        )
        8 -> Color(
            red = cleanedHex.substring(2, 4).toInt(16),
            green = cleanedHex.substring(4, 6).toInt(16),
            blue = cleanedHex.substring(6, 8).toInt(16),
            alpha = cleanedHex.substring(0, 2).toInt(16)
        )
        else -> throw IllegalArgumentException("Invalid hex color format")
    }
}