package com.came.parkare.dashboardapp.ui.components.itemStyles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.ui.theme.LicensePlateFont
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.license_frame
import org.jetbrains.compose.resources.painterResource

@Composable
fun LicensePlateItemStyle(
    plateNumber: String,
    scaleFactor: Float,
    textSize: Float,
    fontWeight: FontWeight,
    textColor: Color,
    padding: Float,
    countryCode: String = "EU",
    modifier: Modifier = Modifier
) {
    val plateHeight = (100f * scaleFactor).dp
    val bandWidth = (48f * scaleFactor).dp
    val borderWidth = (3f * scaleFactor).dp
    val cornerRadius = (6f * scaleFactor).dp
    val horizontalPadding = (16f * scaleFactor).dp

    val starSize = (8f * scaleFactor).sp
    val miniStarSize = (5f * scaleFactor).sp
    val countryCodeSize = (28f * scaleFactor).sp

    val plateShape = RoundedCornerShape(cornerRadius)

    Row(
        modifier = modifier
            .heightIn(max = plateHeight)
            .fillMaxWidth()
            .border(width = borderWidth, color = Color(0xFF1A1A1A), shape = plateShape)
            .background(color = Color.White, shape = plateShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Banda azul izquierda
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(bandWidth)
                .background(
                    color = Color(0xFF003399),
                    shape = RoundedCornerShape(topStart = cornerRadius, bottomStart = cornerRadius)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "★", color = Color(0xFFFFCC00), fontSize = starSize, fontWeight = FontWeight.Bold)
                Text(text = "✦✦✦", color = Color(0xFFFFCC00), fontSize = miniStarSize)
                Text(text = countryCode, color = Color.White, fontSize = countryCodeSize, fontWeight = FontWeight.ExtraBold, letterSpacing = (0.1f * scaleFactor).sp)
                Text(text = "✦✦✦", color = Color(0xFFFFCC00), fontSize = miniStarSize)
                Text(text = "★", color = Color(0xFFFFCC00), fontSize = starSize, fontWeight = FontWeight.Bold)
            }
        }

        // Número de matrícula
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = horizontalPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = plateNumber,
                fontSize = textSize.sp,
                fontFamily = LicensePlateFont,
                letterSpacing = (0.2f * scaleFactor).sp,
                fontWeight = fontWeight,
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier.padding(padding.dp).fillMaxWidth()
            )
        }
    }
}