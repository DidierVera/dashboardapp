package com.came.parkare.dashboardapp.ui.screens.main

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.came.parkare.dashboardapp.ui.components.isBase64
import com.came.parkare.dashboardapp.ui.components.videos.VideoExoPlayer
import com.came.parkare.dashboardapp.ui.screens.activity.MainActivity
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        LoadBackgroundImage(painterResource(id = R.drawable.dashboard_backgroud))
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
    val items: List<ElementModel> = state.newItems
    if (items.isEmpty()) return
    val boxMargin = state.contentPadding

    LazyColumn(modifier = modifier.padding(boxMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if(showVideoFrame) Arrangement.Bottom else Arrangement.Center
    ){
        items.forEach { mItem ->
            item {
                val textSizeScale = state.textSizeScale
                BuildComposable(elementModel = mItem, textSizeScale = textSizeScale)
            }
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

private fun setLowBrightness(activity: Activity) {
    try {
        println("Start bright low down")
        val layout: WindowManager.LayoutParams? = activity.window?.attributes
        layout?.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF
        activity.window?.attributes = layout
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun brightnessBackHigh(activity: Activity) {
    try {
        println("Bright high back")
        val layout: WindowManager.LayoutParams? = activity.window?.attributes
        layout?.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
        // OR set to your desired high brightness:
        activity.window?.attributes = layout
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
