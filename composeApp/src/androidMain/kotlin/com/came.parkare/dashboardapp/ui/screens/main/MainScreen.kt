package com.came.parkare.dashboardapp.ui.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.came.parkare.dashboardapp.R
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.components.BuildComposable
import com.came.parkare.dashboardapp.ui.components.NetworkIndicatorView
import com.came.parkare.dashboardapp.ui.components.carcounter.CarCounterView
import com.came.parkare.dashboardapp.ui.components.isBase64
import com.came.parkare.dashboardapp.ui.components.videos.VideoExoPlayer
import com.came.parkare.dashboardapp.ui.screens.activity.MainActivity
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()) {
    val showVideoFrame by viewModel.showVideoFrame.collectAsState()
    Box(Modifier.fillMaxSize()) {
        LoadBackground()
        NetworkIndicatorView(Modifier.padding(4.dp))

        UpdateDataByLang()
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom),
            modifier = Modifier.fillMaxSize()
        ) {
            LoadDashboardItems(modifier = Modifier.weight(0.7f))
            CarCounterView(modifier = Modifier.align(Alignment.CenterHorizontally).weight(0.3f))

            if(showVideoFrame) VideoExoPlayer(modifier = Modifier.weight(0.3f))
        }
        StartBrightnessTimeout()
    }
}

@Composable
private fun UpdateDataByLang(
    viewModel: MainViewModel = koinViewModel()
){
    val state by viewModel.itemsState.collectAsState()
    if (state.currentLang.isNotBlank()){
        viewModel.getTranslationText(state.currentLang)
    }
}


@Composable
private fun LoadBackground(
    viewModel: MainViewModel = koinViewModel()
){
    Log.i("DASHBOARD_LOG", "Entra en LoadBackground")
    val customBackground by viewModel.backgroundState.collectAsState()

    if (customBackground.isNotBlank()){
        if (customBackground.isBase64()) {
            Base64Image(
                base64String = customBackground,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlackColor)
            )
        }else{
            LoadBackgroundImage(rememberAsyncImagePainter(model = customBackground))
        }
    }else{
        LoadBackgroundImage(painterResource(id = R.drawable.intertraffic_background_image))
    }
}

@Composable
fun LoadBackgroundImage(painter: Painter){
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxSize()
            .background(BlackColor)
    )
}

@Composable
private fun LoadDashboardItems(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
){
    val state by viewModel.itemsState.collectAsState()
    val showVideoFrame by viewModel.showVideoFrame.collectAsState()
    val showCarCounter by viewModel.showCarCounter.collectAsState()
    val elements: List<ElementModel> = state.newItems
    if (elements.isEmpty()) return
    val boxMargin = state.contentPadding

    Column(modifier = modifier.padding(boxMargin).verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if(showVideoFrame || showCarCounter) Arrangement.Bottom else Arrangement.Center
    ){
        elements.forEach { mItem ->
            val textSizeScale = state.textSizeScale
            BuildComposable(elementModel = mItem, textSizeScale = textSizeScale)
        }
    }
}
@Composable
private fun StartBrightnessTimeout() {
    val viewModel: MainViewModel = koinViewModel()
    val isActiveBrightnessMode by viewModel.startBrightnessMode.collectAsState()
    val brightnessWaitDelay by viewModel.delayBrightnessTimeout.collectAsState()
    val activity = (LocalContext.current as MainActivity)

    LaunchedEffect(key1 = isActiveBrightnessMode, key2 = brightnessWaitDelay) {
        if (isActiveBrightnessMode) {
            println("delay time: $brightnessWaitDelay")
            delay(brightnessWaitDelay * 1000L)
            viewModel.setBrightnessByCommand(activity, 1)
        } else {
            viewModel.setBrightnessByCommand(activity, 255)
        }
    }
}