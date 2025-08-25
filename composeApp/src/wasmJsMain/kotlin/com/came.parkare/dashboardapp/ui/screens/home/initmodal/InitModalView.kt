@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.initmodal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.screens.home.HomeViewModel
import com.came.parkare.dashboardapp.ui.screens.home.templates.TemplateScreen
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.create_new_config_label
import dashboardapp.composeapp.generated.resources.edit_existing_config_label
import dashboardapp.composeapp.generated.resources.go_to_setting_panel_label
import dashboardapp.composeapp.generated.resources.modal_initial_label
import dashboardapp.composeapp.generated.resources.request_password_message
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun InitModalView(modifier: Modifier = Modifier,
                  onEditConfig: () -> Unit,
                  onSettingsClick: () -> Unit){
    val viewModel: InitialModalViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box(modifier = modifier.background(Color.DarkGray.copy(alpha = 0.8f)).clickable(enabled = false){})
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.floatingButton().padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(Res.string.modal_initial_label), fontWeight =
                    FontWeight.SemiBold, modifier = Modifier)
                if (!state.showTemplates){
                    InitDirectSetting(onSettingsClick)
                    InitBlankConfig(onEditConfig)
                }
                InitExistingConfig()
            }

            ShowTemplates(onEditConfig)
        }
    }
}

@Composable
private fun InitDirectSetting(onSettingsClick: () -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val message = stringResource(Res.string.request_password_message)
    InitialOption(Res.string.go_to_setting_panel_label,
        Icons.AutoMirrored.Filled.KeyboardArrowRight
    ){
        viewModel.showRequestLogin(message){
            onSettingsClick.invoke()
        }
    }
}

@Composable
private fun InitialOption(textId: StringResource,
    icon: ImageVector,
    onItemClicked: () -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth().floatingButton{
            onItemClicked.invoke()
        }.padding(8.dp)) {
        Text(text = stringResource(textId))

        Icon( icon, contentDescription = "continues")
    }
}

@Composable
private fun InitExistingConfig() {
    val viewModel: InitialModalViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    InitialOption(Res.string.edit_existing_config_label,
        if(state.showTemplates) Icons.Default.KeyboardArrowUp else Icons.AutoMirrored.Filled.KeyboardArrowRight){
        viewModel.onEditExistingConfig()
    }
}

@Composable
private fun ShowTemplates(onEditConfig: () -> Unit){
    val viewModel: InitialModalViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if (state.showTemplates){
        TemplateScreen(){
            onEditConfig.invoke()
        }
    }
}

@Composable
private fun InitBlankConfig(onEditConfig: () -> Unit) {
    val viewModel: InitialModalViewModel = koinViewModel()
    InitialOption(Res.string.create_new_config_label, Icons.AutoMirrored.Filled.KeyboardArrowRight){
        viewModel.onCreateNewConfig {
            onEditConfig.invoke()
        }
    }
}
