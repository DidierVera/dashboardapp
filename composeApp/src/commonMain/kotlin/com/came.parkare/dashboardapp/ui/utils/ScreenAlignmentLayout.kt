package com.came.parkare.dashboardapp.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ScreenAlignment

/*
 * Layout of the content of a screen, shared by the monitor (MainScreen) and the preview of the
 * web configurator so both place the elements the same way.
 */

fun ScreenAlignment.verticalArrangement(): Arrangement.Vertical = when (vertical) {
    ScreenAlignment.Vertical.TOP -> Arrangement.Top
    ScreenAlignment.Vertical.CENTER -> Arrangement.Center
    ScreenAlignment.Vertical.BOTTOM -> Arrangement.Bottom
}

fun ScreenAlignment.horizontalAlignment(): Alignment.Horizontal = when (horizontal) {
    ScreenAlignment.Horizontal.START -> Alignment.Start
    ScreenAlignment.Horizontal.CENTER -> Alignment.CenterHorizontally
    ScreenAlignment.Horizontal.END -> Alignment.End
}

/** The margins of a screen grow with the text size scale (10 = 100 %), like the texts. */
fun screenContentPadding(
    marginLeft: Int, marginTop: Int, marginRight: Int, marginBottom: Int, textSizeScale: Int
): PaddingValues {
    val scaleFactor = (textSizeScale / 10f).coerceIn(0.5f, 3f)
    // A negative margin in the JSON would make Compose throw: it is drawn as 0
    fun scaled(margin: Int) = (margin.coerceAtLeast(0) * scaleFactor).dp
    return PaddingValues(scaled(marginLeft), scaled(marginTop), scaled(marginRight), scaled(marginBottom))
}
