package com.cameparkare.dashboardapp.ui.components.videos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.cameparkare.dashboardapp.ui.theme.BlackColor
import com.cameparkare.dashboardapp.ui.theme.WhiteColor

@Composable
fun ExoVideosContainer(modifier: Modifier, playerView: PlayerView) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp))
        .background(BlackColor)
        .padding(2.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(WhiteColor)
        .padding(1.dp)
        .clip(RoundedCornerShape(4.dp))){

        AndroidView(
            modifier = modifier.fillMaxSize(),
            factory = {
                playerView
            }
        )
    }
}