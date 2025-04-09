@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.connection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.AppLabel
import com.came.parkare.dashboardapp.ui.components.CustomDropdownSelector
import com.came.parkare.dashboardapp.ui.screens.settings.components.DialogPickerDialog
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.api_label
import dashboardapp.composeapp.generated.resources.connection_title
import dashboardapp.composeapp.generated.resources.connection_way_label
import dashboardapp.composeapp.generated.resources.delay_time_label
import dashboardapp.composeapp.generated.resources.image_resources_label
import dashboardapp.composeapp.generated.resources.port_label
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.show_video_frame_label
import dashboardapp.composeapp.generated.resources.terminal_ip_label
import dashboardapp.composeapp.generated.resources.text_size_scale_label
import dashboardapp.composeapp.generated.resources.upload_file_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ConnectionTab() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.connection_title)

        Column {
            AppLabel(Res.string.connection_way_label)
            ConnectionWayControl(state.connectionWayOptions.map { it.second },
                state.connectionWay.second)//connection way
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            TextField(//terminal ip
                value = state.terminalIp,
                onValueChange = { viewModel.setTerminalIp(it) },
                label = { AppLabel(Res.string.terminal_ip_label) }
            )
            TextField(//port
                value = "${state.port}",
                onValueChange ={ viewModel.setPort(it.toInt()) },
                label = { AppLabel(Res.string.port_label) }
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(//api
                value = state.api,
                onValueChange ={ viewModel.setApi(it) },
                label = { AppLabel(Res.string.api_label) }
            )
            TextField(//delay
                value = "${state.delayTime}",
                onValueChange ={ viewModel.setDelayTime(it.toInt()) },
                label = { AppLabel(Res.string.delay_time_label) }
            )
        }
        TextField(//text size scale
            value = "${state.textSizeScale}",
            onValueChange ={ viewModel.textSizeScale(it.toInt()) },
            label = { AppLabel(Res.string.text_size_scale_label) }
        )

        ShowVideoFrame(state.showVideoFrame)

        UploadImages()

        Spacer(modifier = Modifier.height(4.dp))

        SaveButton(modifier = Modifier.align(Alignment.End))

    }
}

@Composable
fun UploadImages() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        AppLabel(Res.string.image_resources_label)
        DialogPickerDialog(
            buttonText = Res.string.upload_file_button,
            multipleFiles = true,
            clearFiles = state.clearSelectedFiles,
            onFilesSelected =  { items ->
                viewModel.setImages(items)
            }
        )
    }
}

@Composable
fun SaveButton(modifier: Modifier = Modifier) {
    val viewModel: ConnectionViewModel = koinViewModel()
    // Save button
    AppButton(
        text = stringResource(Res.string.save_button),
        onClick = {
            viewModel.saveChanges()
        },
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = CameBlueColor,
            contentColor = WhiteColor
        ), modifier = modifier
    )
}

@Composable
fun ConnectionWayControl(options: List<String>, connectionWay: String) {
    val viewModel: ConnectionViewModel = koinViewModel()
    CustomDropdownSelector(
        items = options,
        selectedItem = connectionWay,
        onItemSelected = { viewModel.setConnectionWay(it) },
        itemContent = { item ->
            Row {
                Spacer(Modifier.width(8.dp))
                Text(text = item)
            }
        },
        selectedItemContent = { device ->
            Text(device, fontWeight = FontWeight.Bold)
        }
    )
}

@Composable
private fun ShowVideoFrame(showVideo: Boolean) {
    val viewModel: ConnectionViewModel = koinViewModel()
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            stringResource(Res.string.show_video_frame_label)
        )
        Checkbox(
            checked = showVideo,
            onCheckedChange = { viewModel.setShowVideoFrame(it) }
        )
    }
}

