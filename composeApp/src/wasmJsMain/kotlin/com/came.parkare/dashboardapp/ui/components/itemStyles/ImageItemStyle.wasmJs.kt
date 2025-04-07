package com.came.parkare.dashboardapp.ui.components.itemStyles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
//import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(InternalResourceApi::class)
@Composable
actual fun imageItemStyle(route: String): Painter {
    return when(route.toIntOrNull() != null){
        true -> painterResource(DrawableResource(route, setOf()))
        else -> painterResource(DrawableResource(route, setOf()))
    }
}