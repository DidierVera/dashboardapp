@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.runtime.Composable
import com.came.parkare.dashboardapp.ui.components.MainTopBar
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.request_password_message
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
    val viewModel: HomeViewModel = koinViewModel()
    val message = stringResource(Res.string.request_password_message)

    viewModel.showRequestLogin(message){
        onSettingsClick.invoke()
    }

    MainTopBar {
        viewModel.showRequestLogin(message) {
            onSettingsClick.invoke()
        }
    }
}