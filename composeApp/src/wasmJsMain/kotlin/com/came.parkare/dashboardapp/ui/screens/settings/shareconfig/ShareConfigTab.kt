@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.came.parkare.dashboardapp.ui.screens.settings.importfile.ImportViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ShareConfigTab() {
    val viewModel: ShareConfigViewModel = koinViewModel()
    val state = viewModel.state.collectAsState()

}