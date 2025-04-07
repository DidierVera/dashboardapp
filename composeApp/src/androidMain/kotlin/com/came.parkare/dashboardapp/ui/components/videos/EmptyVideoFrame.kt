package com.came.parkare.dashboardapp.ui.components.videos

import android.os.Environment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor


@Composable
fun EmptyVideoFrame(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp))
        .background(BlackColor)
        .padding(2.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(WhiteColor)
        .padding(1.dp)
        .clip(RoundedCornerShape(4.dp))){
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "VIDEO FRAME",
                color = Color.Red)
            Text(text = "Be sure you put the videos in the route:\r\n ${Environment.DIRECTORY_MOVIES}/Dashboard/videos/",
                color = BlackColor
            )
        }
    }
}