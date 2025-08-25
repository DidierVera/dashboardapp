@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.components.statusbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun StatusBarView(modifier: Modifier = Modifier){
    val viewModel: StatusBarViewModel = koinViewModel()
    viewModel.initStatusBar()
    val state by viewModel.state.collectAsState()

    Row(modifier = modifier.floatingButton(isSelected = true).fillMaxWidth()
        .padding(4.dp)) {
        Text(text = state.templateName.uppercase())
        if (state.editingElementType.isNotEmpty()){
            Text(text = " > ${state.editingElementType}")
        }
    }
}