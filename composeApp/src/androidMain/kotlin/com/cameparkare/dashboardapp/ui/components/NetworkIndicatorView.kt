package com.cameparkare.dashboardapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.R
import com.cameparkare.dashboardapp.ui.screens.main.MainViewModel
import com.cameparkare.dashboardapp.ui.theme.GreenColor
import com.cameparkare.dashboardapp.ui.theme.WarningColor
import org.koin.androidx.compose.koinViewModel


@Composable
fun NetworkIndicatorView(modifier: Modifier, viewModel: MainViewModel = koinViewModel()){
    val state by viewModel.itemsState.collectAsState()
    val isConnected: Boolean = state.statusConnection
    when(isConnected){
        true -> {
            Box(modifier = modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .size(4.dp)
                .background(GreenColor))
        }
        false -> {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ico_wifi_off),
                contentDescription = null,
                tint = WarningColor,
                modifier = modifier
                    .size(30.dp)
                //.background(WarningColor)
            )
        }
    }
}

@Preview
@Composable
private fun NetworkIndicatorPreview(){
    NetworkIndicatorView(modifier = Modifier)
}