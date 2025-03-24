package com.cameparkare.dashboardapp.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.cameparkare.dashboardapp.ui.screens.settings.components.LeftOptionsPanel
import com.cameparkare.dashboardapp.ui.screens.settings.components.SettingTopBar
import com.cameparkare.dashboardapp.ui.screens.settings.viewmodels.SettingViewModel
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(onBackClick: () -> Unit, onSaveClick: () -> Unit){
    val viewModel: SettingViewModel = koinInject()
    val optionsState = viewModel.optionsState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        SettingTopBar(onBackClick, onSaveClick)

        Row {
            LeftOptionsPanel(optionsState.value){ option ->
                viewModel.selectItem(option)
            }

        }
    }

}