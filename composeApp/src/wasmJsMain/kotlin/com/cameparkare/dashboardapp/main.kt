package com.cameparkare.dashboardapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.cameparkare.dashboardapp.config.di.wasmAppModule
import com.cameparkare.dashboardapp.ui.navigation.AppContent
import kotlinx.browser.document
import org.koin.core.context.GlobalContext.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(wasmAppModule)
    }

    ComposeViewport(document.body!!) {
        AppContent()
    }
}