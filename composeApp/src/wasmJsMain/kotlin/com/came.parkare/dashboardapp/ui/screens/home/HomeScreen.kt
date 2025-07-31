@file:OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)

package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.MainTopBar
import com.came.parkare.dashboardapp.ui.screens.home.components.TooltipButton
import com.came.parkare.dashboardapp.ui.screens.home.configeditor.ConfigEditorScreen
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.DefaultElementsList
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.ElementList
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.blank_elements_label
import dashboardapp.composeapp.generated.resources.default_elements_label
import dashboardapp.composeapp.generated.resources.element_code_label
import dashboardapp.composeapp.generated.resources.properties_label
import dashboardapp.composeapp.generated.resources.request_password_message
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
    val viewModel: HomeViewModel = koinViewModel()
    val message = stringResource(Res.string.request_password_message)
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
        Scaffold(
            bottomBar = { BottomBarButtons() },
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ConfigEditorScreen(modifier = Modifier.fillMaxSize())
                ElementList(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())
                DefaultElementsList(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())

            }
        }
    }
}

@Composable
fun BottomBarButtons() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        TooltipButton(onClick = {viewModel.displayBlankElement()},
            value = state.displayBlankElements, tooltipTextId = Res.string.blank_elements_label){
            Icon(if(state.displayBlankElements) Icons.Default.Close else Icons.Default.Create,
                contentDescription = null)
        }
        TooltipButton(onClick = {viewModel.displayProperties()},
            value = state.displayProperties, tooltipTextId = Res.string.properties_label){
            Icon(if(state.displayProperties) Icons.Default.Close else Icons.Default.Build,
                contentDescription = null)
        }
        TooltipButton(onClick = {viewModel.displayElements()},
            value = state.displayDefaultElements, tooltipTextId = Res.string.default_elements_label){
            Icon(if(state.displayDefaultElements) Icons.Default.Close else Icons.Default.Favorite,
                contentDescription = null)
        }
    }
}
