package com.came.parkare.dashboardapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.came.parkare.dashboardapp.ui.components.loading.AppLoading
import com.came.parkare.dashboardapp.ui.screens.home.HomeScreen
import com.came.parkare.dashboardapp.ui.screens.settings.SettingsScreen
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun AppContent() {
    val navigator: Navigator = remember { getKoin().get() }
    // or using the Koin Compose extension:
    // val navigator: Navigator = koinInject()

    when (val currentScreen = navigator.screens.lastOrNull() ?: WasmScreen.Home) {
        is WasmScreen.Home -> HomeScreen(
            onSettingsClick = {
                navigator.navigateTo(WasmScreen.Settings)
            }
        )
        is WasmScreen.Settings -> SettingsScreen(
            onBackClick = {navigator.goBack()},
            onSaveClick = {navigator.goBack()}
        )
    }
    AppLoading()
}