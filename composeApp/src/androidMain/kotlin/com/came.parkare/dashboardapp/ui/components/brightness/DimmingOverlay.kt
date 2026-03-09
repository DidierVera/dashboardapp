package com.came.parkare.dashboardapp.ui.components.brightness

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.came.parkare.dashboardapp.ui.screens.main.MainViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun DimmingOverlay(isDimmed: Boolean) {
    val viewModel: MainViewModel = koinViewModel()
    val brightnessWaitDelay by viewModel.delayBrightnessTimeout.collectAsState()
    val shadowVisibility = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = isDimmed, key2 = brightnessWaitDelay) {
        if (isDimmed) {
            println("delay time: $brightnessWaitDelay")
            delay(brightnessWaitDelay * 1000L)

            shadowVisibility.value = true
        } else {
            shadowVisibility.value = false
        }
    }
    AnimatedVisibility(
        visible = shadowVisibility.value,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.85f))
                .zIndex(Float.MAX_VALUE)
        )
    }
}