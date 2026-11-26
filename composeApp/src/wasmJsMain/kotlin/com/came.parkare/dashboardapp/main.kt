package com.came.parkare.dashboardapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.came.parkare.dashboardapp.config.di.wasmAppModule
import com.came.parkare.dashboardapp.config.utils.RuntimeConfigLoader
import com.came.parkare.dashboardapp.ui.navigation.AppContent
import kotlinx.browser.document
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.context.GlobalContext.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // The API port must be known before the first request of the app
    RuntimeConfigLoader.load { startApp() }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun startApp() {
    ComposeViewport(document.body!!) {
        KoinApplication(application = {
            modules(wasmAppModule)
        }) {
            AppContent()
        }
    }
}