package com.came.parkare.dashboardapp.ui.components.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.BoxDataModel
import com.came.parkare.dashboardapp.ui.components.BuildComposable
import com.came.parkare.dashboardapp.ui.theme.hexToColor

@Composable
fun BuildBoxView(modifier: Modifier = Modifier, box: BoxDataModel, textSizeScale: Int, scaleFactor: Float) {
    Box(
        modifier = modifier
            .shadow(
                elevation = if (box.style.hasShadow) 1.5.dp else 0.dp,
                shape = RoundedCornerShape(box.style.roundBorder.dp)
            )
            .background(
                hexToColor(box.style.backgroundColor)
                    .copy(alpha = (box.style.density / 100f))
            )
            .fillMaxWidth()
            .padding(((box.style.padding ?: 1).toFloat() * scaleFactor).dp), contentAlignment = Alignment.Center
    ) {
        // Recursively build content Composable
        Box(modifier = Modifier.align(Alignment.Center)) {
            box.content.forEach { contentDto ->
                BuildComposable(contentDto, textSizeScale)
            }
        }
    }
}