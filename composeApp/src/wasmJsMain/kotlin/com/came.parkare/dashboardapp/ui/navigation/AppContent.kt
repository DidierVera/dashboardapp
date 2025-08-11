package com.came.parkare.dashboardapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialog
import com.came.parkare.dashboardapp.ui.components.loading.AppLoading
import com.came.parkare.dashboardapp.ui.components.messages.AppToast
import com.came.parkare.dashboardapp.ui.screens.home.HomeScreen
import com.came.parkare.dashboardapp.ui.screens.home.initmodal.InitModalView
import com.came.parkare.dashboardapp.ui.screens.settings.SettingsScreen
import kotlinx.browser.window
import org.koin.mp.KoinPlatform.getKoin
import org.w3c.dom.events.Event

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navigator: Navigator = remember { getKoin().get() }
    Box(modifier = modifier.fillMaxSize()) {
        when (val currentScreen = navigator.screens.lastOrNull() ?: WasmScreen.InitModal) {
            is WasmScreen.Home -> HomeScreen(
                onSettingsClick = {
                    navigator.navigateTo(WasmScreen.Settings)
                },
                onBackClick = {
                    navigator.goBack()
                }
            )
            is WasmScreen.Settings -> SettingsScreen(
                onBackClick = {
                    navigator.goBack()
                }
            )

            WasmScreen.InitModal -> InitModalView(modifier = Modifier.fillMaxSize(), onSettingsClick = {
                navigator.navigateTo(WasmScreen.Settings)
            }, onEditConfig = {
                navigator.navigateTo(WasmScreen.Home)
            })
        }
        AppLoading()
        AppDialog(modifier = Modifier.align(Alignment.Center))
        AppToast(modifier = Modifier.align(Alignment.TopCenter))
    }
}


@Composable
fun rememberWindowWidth(): Int {
    val width = remember { mutableStateOf(window.innerWidth) }

    DisposableEffect(Unit) {
        val listener: (Event) -> Unit = {
            width.value = window.innerWidth
        }

        window.addEventListener("resize", listener)
        onDispose { window.removeEventListener("resize", listener) }
    }

    return width.value
}