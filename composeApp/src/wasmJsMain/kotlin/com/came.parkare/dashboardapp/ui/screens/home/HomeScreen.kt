package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.runtime.Composable
import com.came.parkare.dashboardapp.ui.components.MainTopBar
import org.koin.compose.koinInject

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
    val viewModel: HomeViewModel = koinInject()
    MainTopBar(onSettingsClick)
}