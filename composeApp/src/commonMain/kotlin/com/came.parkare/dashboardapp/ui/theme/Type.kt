package com.came.parkare.dashboardapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.FontFamily


expect val LicensePlateFont: FontFamily
expect val ArialNarrowFont: FontFamily
expect val ArialRoundedFont: FontFamily
expect val Rubik: FontFamily
expect val Acumin: FontFamily

expect val Typography: Typography

expect val LocalAppFontFamily: ProvidableCompositionLocal<FontFamily>
