package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.components.ImageDataModel
import com.came.parkare.dashboardapp.domain.models.components.TextDataModel
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.components.elements.BuildImageView
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.ic_download
import dashboardapp.composeapp.generated.resources.ic_picture
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoadElementImage(imageDataModel: ImageDataModel, model: ImagesFileModel?, scaleFactor: Float){
    if (model != null){
        Base64Image(model.fileContent.orEmpty(),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(((imageDataModel.style.width ?: 1).toFloat() * scaleFactor).dp,
                    ((imageDataModel.style.height ?: 1).toFloat() * scaleFactor).dp)
        )
    }else{
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.shadowContainer()){
            Icon(painter = painterResource(Res.drawable.ic_picture),
                contentDescription = null,
                modifier = Modifier.size((imageDataModel.style.width?:15).dp, (imageDataModel.style.height?:15).dp))
            Text(text = imageDataModel.fileName ?: "image name")
        }
    }
}