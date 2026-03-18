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
import com.came.parkare.dashboardapp.ui.theme.Acumin
import com.came.parkare.dashboardapp.ui.theme.LocalAppFontFamily
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
        LicensePlateItemStyle(
            plateNumber = text.defaultText,
            scaleFactor = scaleFactor,
            modifier = modifier,
            textSize = textSize,
            fontWeight = weight,
            textColor = hexToColor(text.textColor),
            padding = ((text.style.padding ?: 1).toFloat() * scaleFactor)
        )

    } else {
        val animatedText = animateFloatAsState(targetValue = scaleFactor)
        val appFontFamily = LocalAppFontFamily.current
        Text(
            text = text.defaultText,
            fontSize = textSize.sp * animatedText.value,
            fontFamily = appFontFamily,
            fontWeight = weight,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Center,
            color = hexToColor(text.textColor),
            modifier = modifier.padding(((text.style.padding ?: 1).toFloat() * scaleFactor).dp)
        )
    }
}