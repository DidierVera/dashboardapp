package com.came.parkare.dashboardapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialog
import com.came.parkare.dashboardapp.ui.components.loading.AppLoading
import com.came.parkare.dashboardapp.ui.components.messages.AppToast
import com.came.parkare.dashboardapp.ui.screens.home.HomeScreen
import com.came.parkare.dashboardapp.ui.screens.settings.SettingsScreen
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navigator: Navigator = remember { getKoin().get() }
    Box(modifier = modifier.fillMaxSize()) {
        when (val currentScreen = navigator.screens.lastOrNull() ?: WasmScreen.Home) {
            is WasmScreen.Home -> HomeScreen(
                onSettingsClick = {
                    navigator.navigateTo(WasmScreen.Settings)
                }
            )
            is WasmScreen.Settings -> SettingsScreen(
                onBackClick = {navigator.goBack()}
            )
        }
        AppLoading()
        AppDialog(modifier = Modifier.align(Alignment.Center))
        AppToast(modifier = Modifier.align(Alignment.TopCenter))
    }
}