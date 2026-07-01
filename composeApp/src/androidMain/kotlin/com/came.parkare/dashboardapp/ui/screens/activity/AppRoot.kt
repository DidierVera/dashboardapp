package com.came.parkare.dashboardapp.ui.screens.activity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.came.parkare.dashboardapp.ui.screens.main.MainScreen
import com.came.parkare.dashboardapp.ui.theme.LocalAppFontFamily
import com.came.parkare.dashboardapp.ui.utils.FontViewModel
import org.koin.compose.koinInject


@Composable
fun AppRoot() {
    val fontViewModel: FontViewModel = koinInject() // ← koinInject for singleton
    val fontFamily by fontViewModel.fontFamily.collectAsState()

    CompositionLocalProvider(LocalAppFontFamily provides fontFamily) {
        MainScreen()
    }
}