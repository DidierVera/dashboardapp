package com.cameparkare.dashboardapp.ui.components.itemStyles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

@Composable
actual fun imageItemStyle(route: String): Painter {
    return when(route.toIntOrNull() != null){
        true -> painterResource(id = route.toInt())
        else -> rememberAsyncImagePainter(model = route)
    }
}