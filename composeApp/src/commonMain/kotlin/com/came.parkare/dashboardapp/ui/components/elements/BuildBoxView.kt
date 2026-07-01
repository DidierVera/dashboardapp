package com.came.parkare.dashboardapp.ui.components.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.BoxDataModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.BuildComposable
import com.came.parkare.dashboardapp.ui.theme.hexToColor
@Composable
fun BuildBoxView(
    modifier: Modifier = Modifier,
    box: BoxDataModel,
    textSizeScale: Int,
    scaleFactor: Float,
    buildChildren: @Composable (ElementModel, Int) -> Unit
) {
    val backgroundColor = runCatching { hexToColor(box.style.backgroundColor) }
        .getOrDefault(Color.Transparent)
        .copy(alpha = (box.style.density / 100f))

    Box(
        modifier = modifier
            .padding(((box.style.margin ?: 0).toFloat() * scaleFactor).dp)
            .shadow(
                elevation = if (box.style.hasShadow) 1.5.dp else 0.dp,
                shape = RoundedCornerShape(box.style.roundBorder.dp)
            )
            .background(backgroundColor)
            .run {
                val withWidth = if (box.style.width != null) width((box.style.width.toFloat() * scaleFactor).dp) else fillMaxWidth()
                if (box.style.height != null) withWidth.height((box.style.height.toFloat() * scaleFactor).dp) else withWidth
            }
            .padding(((box.style.padding ?: 0).toFloat() * scaleFactor).dp),
        contentAlignment = Alignment.Center
    ) {
        box.content.forEach { contentDto ->
            buildChildren(contentDto, textSizeScale)
        }
    }
}