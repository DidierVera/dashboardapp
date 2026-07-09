package com.came.parkare.dashboardapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

actual val LicensePlateFont: FontFamily
    @Composable
    get() = FontFamily.Default

actual val ArialNarrowFont: FontFamily
    @Composable
    get() = FontFamily.Default

actual val ArialRoundedFont: FontFamily
    @Composable
    get() = FontFamily.Default

actual val Acumin: FontFamily
    @Composable
    get() = FontFamily.Default

actual val LocalAppFontFamily = compositionLocalOf<FontFamily> { FontFamily.Default }

actual val Rubik: FontFamily
    @Composable
    get() = FontFamily.Default

actual val Typography: Typography
    @Composable
    get() = Typography(
        titleMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        displayLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center
        )
    )
