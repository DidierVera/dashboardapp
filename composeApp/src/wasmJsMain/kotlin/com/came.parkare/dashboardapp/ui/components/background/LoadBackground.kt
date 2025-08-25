package com.came.parkare.dashboardapp.ui.components.background

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.ui.components.Base64Image
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.dashboard_backgroud
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoadBackground(background: ImagesFileModel?) {
    if(background != null){
        Base64Image(background.fileContent.orEmpty(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth)
    }else {
        Image(
            painter = painterResource(Res.drawable.dashboard_backgroud),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(4.dp).fillMaxSize()
                .background(Color.LightGray)
        )
    }
}