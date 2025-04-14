@file:OptIn(KoinExperimentalAPI::class)
package com.came.parkare.dashboardapp.ui.components.messages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.LightBlackColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun AppToast(){
    val viewModel: AppToastViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if(state) {
        Box(
            modifier = Modifier.fillMaxSize().background(LightBlackColor.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = CameBlueColor)
                Spacer(Modifier.height(8.dp))
                Text("Loading...", color = WhiteColor)
            }
        }
    }

    val scope = rememberCoroutineScope() // Create a coroutine scope
    var progress by remember { mutableStateOf(0.1f) }


    LaunchedEffect(key1 = true){
        scope.launch {
            for (i in 1..100) {
                progress = i.toFloat() / 100
                delay(10)
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BlackColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Spacer(modifier = Modifier.height(55.dp))

        LinearProgressIndicator(modifier = Modifier.width(300.dp)
            ,color = CameBlueColor
            , progress = { progress })
    }
}