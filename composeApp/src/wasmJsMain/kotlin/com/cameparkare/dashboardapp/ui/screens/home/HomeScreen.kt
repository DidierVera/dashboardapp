package com.cameparkare.dashboardapp.ui.screens.home

import androidx.compose.runtime.Composable
import com.cameparkare.dashboardapp.ui.components.MainTopBar

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
    MainTopBar(onSettingsClick)
}