package com.cameparkare.dashboardapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.cameparkare.dashboardapp.ui.screens.details.DetailsScreen
import com.cameparkare.dashboardapp.ui.screens.home.HomeScreen
import com.cameparkare.dashboardapp.ui.screens.main.MainScreen
import com.cameparkare.dashboardapp.ui.screens.settings.SettingsScreen
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
        is WasmScreen.Details -> DetailsScreen(
            id = currentScreen.id,
            onBack = { navigator.goBack() }
        )

        is WasmScreen.MainScreen -> MainScreen()
        is WasmScreen.Settings -> SettingsScreen(
            onBackClick = {navigator.goBack()},
            onSaveClick = {navigator.goBack()}
        )
    }
}