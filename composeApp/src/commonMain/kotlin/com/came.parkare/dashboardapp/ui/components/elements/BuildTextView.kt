package com.came.parkare.dashboardapp.ui.components.elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.domain.models.components.TextDataModel
import com.came.parkare.dashboardapp.ui.components.itemStyles.LicensePlateItemStyle
import com.came.parkare.dashboardapp.ui.theme.LicensePlateFont
import com.came.parkare.dashboardapp.ui.theme.Rubik
import com.came.parkare.dashboardapp.ui.theme.hexToColor

@Composable
fun BuildTextView(text: TextDataModel, scaleFactor: Float, modifier: Modifier = Modifier) {
    val weight = when {
        text.fontWeight.lowercase().contains("bold") -> FontWeight.Bold
        text.fontWeight.lowercase().contains("medium") -> FontWeight.Medium
        else -> FontWeight.Normal
    }
    val textSize = (text.textSize.toFloat() * scaleFactor)
        .coerceAtLeast((text.textSize - 8).toFloat())
    if (text.dashboardItemId.contains("license-plate-value")) {
        println("license-plate-value==== ${text.defaultText}")
        LicensePlateItemStyle(modifier = modifier) { mdf ->
            Text(
                text = text.defaultText,
                fontSize = textSize.sp,
                fontFamily = LicensePlateFont,
                letterSpacing = 2.sp,
                fontWeight = weight,
                textAlign = TextAlign.Center,
                color = hexToColor(text.textColor),
                modifier = mdf.padding(0.dp, 0.dp, 0.dp, 10.dp)
                    .padding(((text.style.padding ?: 1).toFloat() * scaleFactor).dp)
            )
        }

    } else {
        val animatedText = animateFloatAsState(targetValue = scaleFactor)
        Text(
            text = text.defaultText,
            fontSize = textSize.sp * animatedText.value,
            fontFamily = Rubik,
            fontWeight = weight,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Center,
            color = hexToColor(text.textColor),
            modifier = modifier.padding(((text.style.padding ?: 1).toFloat() * scaleFactor).dp)
        )
    }
}