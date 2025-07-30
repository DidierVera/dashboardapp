@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.came.parkare.dashboardapp.ui.components.MainTopBar
import com.came.parkare.dashboardapp.ui.screens.home.configeditor.ConfigEditorScreen
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.ElementList
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.request_password_message
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
    val viewModel: HomeViewModel = koinViewModel()
    val message = stringResource(Res.string.request_password_message)
    val state by viewModel.state.collectAsState()
/*
    viewModel.showRequestLogin(message){
        onSettingsClick.invoke()
    }
*/
    Column {
        MainTopBar {
            viewModel.showRequestLogin(message) {
                onSettingsClick.invoke()
            }
        }
        Box(modifier = Modifier) {
            val mainWeight = when(state.isShowingElements && state.isShowingProperties){
                true -> 0.75f
                false -> 1f
            }
            ConfigEditorScreen(modifier = Modifier.fillMaxSize())
            ElementList(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())
        }
    }
}