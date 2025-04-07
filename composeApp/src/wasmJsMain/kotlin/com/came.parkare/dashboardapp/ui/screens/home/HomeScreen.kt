package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.runtime.Composable
import com.came.parkare.dashboardapp.ui.components.MainTopBar

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
    MainTopBar(onSettingsClick)
}