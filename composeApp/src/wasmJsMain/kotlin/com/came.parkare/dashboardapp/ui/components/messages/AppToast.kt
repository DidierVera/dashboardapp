@file:OptIn(KoinExperimentalAPI::class)
package com.came.parkare.dashboardapp.ui.components.messages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.LightBlackColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.ic_close
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun AppToast(modifier: Modifier = Modifier){
    val viewModel: AppToastViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if(state.showMessage) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .heightIn(min = 35.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.floatingButton().width(250.dp).padding(4.dp)) {

                Text(state.message, color = BlackColor, modifier = Modifier.widthIn(max = 200.dp))

                Spacer(Modifier.height(4.dp))

                ShowLinearProgressBar(modifier = Modifier.align(Alignment.Top)) {
                    viewModel.hideMessage()
                }
            }
        }
    }
}

@Composable
fun ShowLinearProgressBar(modifier: Modifier = Modifier, onFinish: () -> Unit) {
    val viewModel: AppToastViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    var linearProgress by remember { mutableStateOf(0.1F) }

    LaunchedEffect(key1 = true){
        scope.launch {
            var progress = 1
            val goal  = state.timer
            while (progress < goal){
                if(state.timer == 250F) progress = 1
                linearProgress =  progress.toFloat() / 250
                delay(20)
                viewModel.setTimer(linearProgress)
                progress++
            }
            onFinish.invoke()
        }
    }
    Box(modifier = modifier.size(24.dp)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).fillMaxSize()
            ,color = CameBlueColor
            , strokeWidth = 1.2.dp
            , progress = { linearProgress })
        IconButton(onClick = { viewModel.hideMessage() },
            modifier = Modifier.align(Alignment.Center).fillMaxSize(0.82F)) {
            Image(painter = painterResource(Res.drawable.ic_close),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.align(Alignment.Center).fillMaxSize())
        }
    }
}
