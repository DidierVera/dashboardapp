package com.cameparkare.dashboardapp.ui.components.itemStyles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.license_frame
import org.jetbrains.compose.resources.painterResource

@Composable
fun LicensePlateItemStyle(
    modifier: Modifier = Modifier,
    size: Dp = (754).dp,
    content:  @Composable (Modifier) -> Unit) {


    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Image(
            painter = painterResource(Res.drawable.license_frame),
            null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(size)
        )
        //text
        content(Modifier.align(Alignment.Center))
    }
}