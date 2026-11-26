package com.came.parkare.dashboardapp.ui.components.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ScreenAlignment
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DisplaySizeDto
import com.came.parkare.dashboardapp.ui.utils.horizontalAlignment
import com.came.parkare.dashboardapp.ui.utils.screenContentPadding
import com.came.parkare.dashboardapp.ui.utils.verticalArrangement
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Preview of a screen as the monitor shows it: the content is laid out at the size of the
 * monitor in dp (margins, alignment and text sizes like MainScreen) and then scaled down to fit
 * in [maxHeight] and the available width.
 */
@Composable
fun ScreenPreview(
    elements: List<ElementModel>,
    marginTop: Int,
    marginBottom: Int,
    marginLeft: Int,
    marginRight: Int,
    contentAlignment: ScreenAlignment,
    displaySize: DisplaySizeDto,
    textSizeScale: Int,
    modifier: Modifier = Modifier,
    maxHeight: Dp = 720.dp,
    background: @Composable () -> Unit,
    element: @Composable (index: Int, element: ElementModel) -> Unit
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val screenWidth = displaySize.widthDp.toFloat()
        val screenHeight = displaySize.heightDp.toFloat()
        val scale = min(maxWidth.value / screenWidth, maxHeight.value / screenHeight)

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size((screenWidth * scale).dp, (screenHeight * scale).dp)
                .border(1.dp, Color.Gray)
                .clipToBounds()
        ) {
            Box(modifier = Modifier.scaledScreen(screenWidth.dp, screenHeight.dp, scale)) {
                background()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(screenContentPadding(marginLeft, marginTop, marginRight, marginBottom, textSizeScale)),
                    horizontalAlignment = contentAlignment.horizontalAlignment(),
                    verticalArrangement = contentAlignment.verticalArrangement()
                ) {
                    elements.forEachIndexed { index, item -> element(index, item) }
                }
            }
        }
    }
}

/** Measures the content at [width] x [height] and draws it scaled by [scale] from the top left corner. */
private fun Modifier.scaledScreen(width: Dp, height: Dp, scale: Float): Modifier = layout { measurable, _ ->
    val widthPx = width.roundToPx()
    val heightPx = height.roundToPx()
    val placeable = measurable.measure(Constraints.fixed(widthPx, heightPx))
    layout((widthPx * scale).roundToInt(), (heightPx * scale).roundToInt()) {
        placeable.placeWithLayer(0, 0) {
            scaleX = scale
            scaleY = scale
            transformOrigin = TransformOrigin(0f, 0f)
        }
    }
}
