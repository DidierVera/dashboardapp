@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.templates

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun TemplateScreen(){
    val viewModel: TemplateViewModel = koinViewModel()
    viewModel.initScreen()
    loadTemplates()
}

@Composable
fun loadTemplates() {
    val viewModel: TemplateViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn {
        items(state.items){ template ->
            Text(text = template.templateName)
        }
    }
}
