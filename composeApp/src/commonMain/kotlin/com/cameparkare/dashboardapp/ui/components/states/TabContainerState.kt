package com.cameparkare.dashboardapp.ui.components.states

import androidx.compose.runtime.Composable

data class TabContainerState(
    val content: @Composable () -> Unit,
    val title: String
)
