@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.actionbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.screens.home.HomeViewModel
import com.came.parkare.dashboardapp.ui.screens.home.components.TooltipButton
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.HeaderColor
import com.came.parkare.dashboardapp.ui.theme.LightGrayColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.back_button_alt
import dashboardapp.composeapp.generated.resources.blank_screen_tooltip
import dashboardapp.composeapp.generated.resources.general_configuration_title
import dashboardapp.composeapp.generated.resources.go_back_tooltip
import dashboardapp.composeapp.generated.resources.ic_add_blank
import dashboardapp.composeapp.generated.resources.ic_back_arrow
import dashboardapp.composeapp.generated.resources.ic_blank
import dashboardapp.composeapp.generated.resources.ic_import_export
import dashboardapp.composeapp.generated.resources.ic_item_arrow
import dashboardapp.composeapp.generated.resources.ic_save
import dashboardapp.composeapp.generated.resources.ic_shapes
import dashboardapp.composeapp.generated.resources.ic_upload
import dashboardapp.composeapp.generated.resources.import_config_tooltip
import dashboardapp.composeapp.generated.resources.request_password_message
import dashboardapp.composeapp.generated.resources.save_config_tooltip
import dashboardapp.composeapp.generated.resources.settings_button
import dashboardapp.composeapp.generated.resources.template_name_label
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun MainTopBar(onSettingsClick: () -> Unit, onBackClick: () -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val message = stringResource(Res.string.request_password_message)
    Box(
        modifier = Modifier.fillMaxWidth().background(HeaderColor).padding(4.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)) {
            TooltipButton(onClick = {
                    viewModel.onCloseEditor()
                    onBackClick.invoke()
                },
                tooltipTextId = Res.string.go_back_tooltip){
                Icon(painter = painterResource(Res.drawable.ic_back_arrow),
                    contentDescription = null, tint = WhiteColor, modifier = Modifier.size(24.dp))
            }
            TooltipButton(onClick = {viewModel.addBlankScreen()},
                tooltipTextId = Res.string.blank_screen_tooltip){
                Icon(painter = painterResource(Res.drawable.ic_add_blank),
                    contentDescription = null, tint = WhiteColor, modifier = Modifier.size(24.dp))
            }
            TooltipButton(onClick = {viewModel.importConfig()},
                tooltipTextId = Res.string.import_config_tooltip){
                Icon(painter = painterResource(Res.drawable.ic_upload),
                    contentDescription = null, tint = WhiteColor, modifier = Modifier.size(24.dp))
            }
            TooltipButton(onClick = {viewModel.saveConfig()},
                tooltipTextId = Res.string.save_config_tooltip){
                Icon(painter = painterResource(Res.drawable.ic_save),
                    contentDescription = null, tint = WhiteColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = state.templateName.uppercase(),
                colors = OutlinedTextFieldDefaults.colors().copy(focusedTextColor = WhiteColor, unfocusedTextColor = WhiteColor.copy(0.6f), focusedLabelColor = WhiteColor, unfocusedLabelColor = LightGrayColor),
                onValueChange = { viewModel.onTemplateNameChange(it) },
                label = { Text(stringResource(Res.string.template_name_label)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier.padding(0.dp)
            )

        }

        Text(text = stringResource(Res.string.general_configuration_title), color = WhiteColor, modifier = Modifier.align(Alignment.Center))

        // Settings button
        AppButton(text = stringResource(Res.string.settings_button), modifier = Modifier.align(
            Alignment.CenterEnd), onClick = {
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