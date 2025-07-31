@file:OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class)

package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ConfigEditorScreen(modifier: Modifier = Modifier,
                       viewModel: ConfigEditorViewModel = koinViewModel()){


}