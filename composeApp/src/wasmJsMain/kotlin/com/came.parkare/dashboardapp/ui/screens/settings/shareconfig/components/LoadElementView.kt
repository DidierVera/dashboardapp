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
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.ImageDataModel
import com.came.parkare.dashboardapp.domain.models.components.TextDataModel
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.ic_download
import dashboardapp.composeapp.generated.resources.ic_picture
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoadElementText(textDataModel: TextDataModel){
    Box(modifier = Modifier.shadowContainer()){
        Text(text = textDataModel.translations?.values?.first() ?: textDataModel.defaultText,
            modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun LoadElementImage(imageDataModel: ImageDataModel){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.shadowContainer()){
        Icon(painter = painterResource(Res.drawable.ic_picture),
            contentDescription = null,
            modifier = Modifier.size((imageDataModel.style.width?:15).dp, (imageDataModel.style.height?:15).dp))
        Text(text = imageDataModel.fileName ?: "image name")
    }

}