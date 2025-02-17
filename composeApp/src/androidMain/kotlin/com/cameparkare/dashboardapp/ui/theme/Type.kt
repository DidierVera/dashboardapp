package com.cameparkare.dashboardapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.cameparkare.dashboardapp.R


val LicensePlateFont = FontFamily(
    Font(resId = R.font.din_condensed_bold)
)
val ArialNarrowFont = FontFamily(
    Font(resId = R.font.arial_narrow_bold, FontWeight.Bold)
)
val ArialRoundedFont = FontFamily(
    Font(resId = R.font.arial_rounded_bold, FontWeight.Bold)
)
val Rubik = FontFamily(
    Font(resId = R.font.rubik_regular, FontWeight.Normal, FontStyle.Normal),
    Font(resId = R.font.rubik_medium, FontWeight.Medium, FontStyle.Normal),
    Font(resId = R.font.rubik_bold, FontWeight.Bold, FontStyle.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = ArialRoundedFont,
        fontWeight = FontWeight.Bold,
        color = WhiteColor,
        textAlign = TextAlign.Center
    ),
    bodyLarge = TextStyle(
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
    displayMedium = TextStyle(
        fontFamily = ArialRoundedFont,
        fontWeight = FontWeight.Bold,
        color = GreenColor,
        textAlign = TextAlign.Center
    ),
    labelLarge = TextStyle(
        fontFamily = ArialNarrowFont,
        color = BlackColor,
        textAlign = TextAlign.Center
    )
)