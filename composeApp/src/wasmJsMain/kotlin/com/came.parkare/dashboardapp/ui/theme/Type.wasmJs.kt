package com.came.parkare.dashboardapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.arial_narrow_bold
import dashboardapp.composeapp.generated.resources.arial_rounded_bold
import dashboardapp.composeapp.generated.resources.mespreg
import dashboardapp.composeapp.generated.resources.rubik_bold
import dashboardapp.composeapp.generated.resources.rubik_medium
import dashboardapp.composeapp.generated.resources.rubik_regular
import org.jetbrains.compose.resources.Font

actual val LicensePlateFont: FontFamily
    @Composable
    get() = FontFamily(Font(Res.font.mespreg))

actual val ArialNarrowFont: FontFamily
    @Composable
    get() = FontFamily(Font(Res.font.arial_narrow_bold, FontWeight.Bold))

actual val ArialRoundedFont: FontFamily
    @Composable
    get() = FontFamily(Font(Res.font.arial_rounded_bold, FontWeight.Bold))

actual val Rubik: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.rubik_regular),
        Font(Res.font.rubik_medium,  FontWeight.Medium),
        Font(Res.font.rubik_bold,  FontWeight.Bold)
        )

actual val Typography: Typography
    @Composable
    get() = Typography(
        titleMedium = TextStyle(
            fontFamily = ArialRoundedFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        bodyMedium = TextStyle(
            fontFamily = ArialNarrowFont,
            fontWeight = FontWeight.Bold,
            color = LightBlackColor,
            textAlign = TextAlign.Center
        ),
        labelMedium = TextStyle(
            fontFamily = ArialRoundedFont,
            fontWeight = FontWeight.Bold,
            color = LightBlackColor,
            textAlign = TextAlign.Center
        ),
        displayLarge = TextStyle(
            fontFamily = ArialRoundedFont,
            fontWeight = FontWeight.Bold,
            color = GreenColor,
            textAlign = TextAlign.Center
        ),
        headlineMedium = TextStyle(
            fontFamily = ArialNarrowFont,
            color = BlackColor,
            textAlign = TextAlign.Center
        )
    )