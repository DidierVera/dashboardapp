package com.came.parkare.dashboardapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.came.parkare.dashboardapp.config.di.wasmAppModule
import com.came.parkare.dashboardapp.ui.navigation.AppContent
import kotlinx.browser.document
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.context.GlobalContext.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
//    startKoin {
//        modules(wasmAppModule)
//    }

    ComposeViewport(document.body!!) {
        KoinApplication(application = {
            modules(wasmAppModule)
        }) {
            AppContent()
        }
    }
}