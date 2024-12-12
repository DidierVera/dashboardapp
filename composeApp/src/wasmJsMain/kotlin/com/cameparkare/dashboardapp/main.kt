package com.cameparkare.dashboardapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.cameparkare.dashboardapp.ui.screens.main.MainScreen
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        MainScreen()
        //App()
    }
}