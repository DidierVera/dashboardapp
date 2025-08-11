@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.actionbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.screens.home.HomeViewModel
import com.came.parkare.dashboardapp.ui.screens.home.components.TooltipButton
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.HeaderColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.back_button_alt
import dashboardapp.composeapp.generated.resources.blank_screen_tooltip
import dashboardapp.composeapp.generated.resources.general_configuration_title
import dashboardapp.composeapp.generated.resources.go_back_tooltip
import dashboardapp.composeapp.generated.resources.import_config_tooltip
import dashboardapp.composeapp.generated.resources.request_password_message
import dashboardapp.composeapp.generated.resources.save_config_tooltip
import dashboardapp.composeapp.generated.resources.settings_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun MainTopBar(onSettingsClick: () -> Unit, onBackClick: () -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val message = stringResource(Res.string.request_password_message)
    Row(
        modifier = Modifier.fillMaxWidth().background(HeaderColor).padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TooltipButton(onClick = {
                    viewModel.onCloseEditor()
                    onBackClick.invoke()
                },
                tooltipTextId = Res.string.go_back_tooltip){
                Icon(Icons.Default.Close,
                    contentDescription = null, tint = WhiteColor)
            }
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