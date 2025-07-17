package com.came.parkare.dashboardapp.ui.components.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.ImageDataModel
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.components.isBase64
import com.came.parkare.dashboardapp.ui.components.itemStyles.imageItemStyle

@Composable
fun BuildImageView(modifier: Modifier = Modifier, image: ImageDataModel, scaleFactor: Float){
    if (!image.folderPath.isNullOrBlank()) {
        if(image.folderPath.isBase64()){
            Base64Image(image.folderPath,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(((image.style.width ?: 1).toFloat() * scaleFactor).dp,
                        ((image.style.height ?: 1).toFloat() * scaleFactor).dp)
            )
        }else{
            Image(
                painter = imageItemStyle(image.folderPath),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(((image.style.width ?: 1).toFloat() * scaleFactor).dp,
                        ((image.style.height ?: 1).toFloat() * scaleFactor).dp)
            )
        }
    } else Unit
}