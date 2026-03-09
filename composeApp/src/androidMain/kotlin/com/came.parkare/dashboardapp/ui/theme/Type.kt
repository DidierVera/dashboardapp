@file:OptIn(ExperimentalTextApi::class)

package com.came.parkare.dashboardapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.came.parkare.dashboardapp.R

actual val LicensePlateFont: FontFamily = FontFamily(
    Font(R.font.mespreg)
)

actual val ArialNarrowFont: FontFamily = FontFamily(
    Font(R.font.arial_narrow_bold, FontWeight.Bold)
)

actual val ArialRoundedFont: FontFamily = FontFamily(
    Font(R.font.arial_rounded_bold, FontWeight.Bold)
)

actual val Rubik: FontFamily = FontFamily(
    Font(R.font.rubik_regular, FontWeight.Normal ),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_bold, FontWeight.Bold)
)
actual val Acumin: FontFamily = FontFamily(
    Font(R.font.acumin, FontWeight.Normal,
        variationSettings = FontVariation.Settings(
            FontVariation.width(80f),
            FontVariation.weight(700)
        )
    )
)

actual val Typography: Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = Acumin,
        fontWeight = FontWeight.Bold,
        color = WhiteColor,
        textAlign = TextAlign.Center
    ),
    bodyLarge = TextStyle(
        fontFamily = Acumin,
        fontWeight = FontWeight.Bold,
        color = LightBlackColor,
        textAlign = TextAlign.Center
    ),
    labelMedium = TextStyle(
        fontFamily = Acumin,
        fontWeight = FontWeight.Bold,
        color = LightBlackColor,
        textAlign = TextAlign.Center
    ),
    displayMedium = TextStyle(
        fontFamily = Acumin,
        fontWeight = FontWeight.Bold,
        color = GreenColor,
        textAlign = TextAlign.Center
    ),
    labelLarge = TextStyle(
        fontFamily = Acumin,
        color = BlackColor,
        textAlign = TextAlign.Center
    )
)