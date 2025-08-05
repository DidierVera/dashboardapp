@file:OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)

package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.screens.home.components.TooltipButton
import com.came.parkare.dashboardapp.ui.screens.home.configeditor.ConfigEditorScreen
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.DefaultElementsList
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.ElementList
import com.came.parkare.dashboardapp.ui.screens.home.properties.PropertiesEditor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.HeaderColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.blank_elements_label
import dashboardapp.composeapp.generated.resources.blank_screen_tooltip
import dashboardapp.composeapp.generated.resources.default_elements_label
import dashboardapp.composeapp.generated.resources.general_configuration_title
import dashboardapp.composeapp.generated.resources.import_config_tooltip
import dashboardapp.composeapp.generated.resources.import_option
import dashboardapp.composeapp.generated.resources.properties_label
import dashboardapp.composeapp.generated.resources.request_password_message
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.save_config_tooltip
import dashboardapp.composeapp.generated.resources.settings_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
/*
    viewModel.showRequestLogin(message){
        onSettingsClick.invoke()
    }
*/
    Column {

        Scaffold(
            topBar = { HomeTopBar(onSettingsClick)},
            bottomBar = { BottomBarButtons() },
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ConfigEditorScreen(modifier = Modifier.fillMaxSize())
                ElementList(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())
                DefaultElementsList(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())
                PropertiesEditor(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())

            }
        }
    }
}

@Composable
fun HomeTopBar(onSettingsClick: () -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val message = stringResource(Res.string.request_password_message)
    Row(
        modifier = Modifier.fillMaxWidth().background(HeaderColor).padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TooltipButton(onClick = {viewModel.displayBlankElement()},
                tooltipTextId = Res.string.blank_screen_tooltip){
                Icon(Icons.Default.Add,
                    contentDescription = null, tint = WhiteColor)
            }
            TooltipButton(onClick = {viewModel.displayBlankElement()},
                tooltipTextId = Res.string.import_config_tooltip){
                Icon(Icons.Default.KeyboardArrowUp,
                    contentDescription = null, tint = WhiteColor)
            }
            TooltipButton(onClick = {viewModel.displayBlankElement()},
                tooltipTextId = Res.string.save_config_tooltip){
                Icon(Icons.Default.Done,
                    contentDescription = null, tint = WhiteColor)
            }

        }

        Text(text = stringResource(Res.string.general_configuration_title), color = WhiteColor)

        // Settings button
        AppButton(text = stringResource(Res.string.settings_button), onClick = {
            viewModel.showRequestLogin(message){
                onSettingsClick.invoke()
            }
        },
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = CameBlueColor,
                contentColor = WhiteColor
            ) )
    }
}

@Composable
fun BottomBarButtons() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TooltipButton(onClick = {viewModel.displayBlankElement()},
                tooltipTextId = Res.string.blank_elements_label){
                Icon(if(state.displayBlankElements) Icons.Default.Close else Icons.Default.Create,
                    contentDescription = null)
            }
            TooltipButton(onClick = {viewModel.displayElements()},
                tooltipTextId = Res.string.default_elements_label){
                Icon(if(state.displayDefaultElements) Icons.Default.Close else Icons.Default.Favorite,
                    contentDescription = null)
            }
        }
        TooltipButton(onClick = {viewModel.displayProperties()},
            tooltipTextId = Res.string.properties_label){
            Icon(if(state.displayProperties) Icons.Default.Close else Icons.Default.Build,
                contentDescription = null)
        }
    }
}
