package com.cameparkare.dashboardapp.ui.components.itemStyles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.R

@Composable
fun LicensePlateItemStyle(
    size: Dp = (754).dp,
    content:  @Composable () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.license_frame),
            null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(size)
        )
        //text
        content()
    }
}
