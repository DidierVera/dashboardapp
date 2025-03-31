package com.cameparkare.dashboardapp.ui.screens.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cameparkare.dashboardapp.MainApplication
import com.cameparkare.dashboardapp.R
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.ui.components.BuildComposable
import com.cameparkare.dashboardapp.ui.components.NetworkIndicatorView
import com.cameparkare.dashboardapp.ui.components.videos.VideoExoPlayer
import com.cameparkare.dashboardapp.ui.theme.BlackColor

import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    Box(Modifier.fillMaxSize()) {
        LoadBackground()
        NetworkIndicatorView(Modifier.padding(4.dp))
        UpdateDataByLang()
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom),
            modifier = Modifier.fillMaxSize()
        ) {
            LoadDashboardItems(modifier = Modifier.weight(0.7f))
            VideoExoPlayer(modifier = Modifier.weight(0.3f))
        }
        RestartApp()
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
private fun RestartApp(
    viewModel: MainViewModel = koinViewModel()
){
    val context = LocalContext.current
    viewModel.onRestartApp{
        val packageManager = context.packageManager
        val intent = packageManager.getLaunchIntentForPackage(context.packageName)
        val componentName = intent?.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        context.startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }
}

@Composable
private fun LoadBackground(
    viewModel: MainViewModel = koinViewModel()
){
    Log.i("DASHBOARD_LOG", "Entra en LoadBackground")
    val customBackground by viewModel.backgroundState.collectAsState()

    val painter = when(customBackground.isBlank()){
        false -> rememberAsyncImagePainter(model = customBackground)
        true -> painterResource(id = R.drawable.dashboard_backgroud)
    }

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
    val items: List<ElementModel> = state.newItems
    if (items.isEmpty()) return
    val boxMargin = state.contentPadding
    val showVideoFrame = viewModel.showVideoFrame()

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