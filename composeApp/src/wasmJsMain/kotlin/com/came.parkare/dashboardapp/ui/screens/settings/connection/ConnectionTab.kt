@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.connection

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.AppLabel
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.components.CustomDropdownSelector
import com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker.DialogPickerDialog
import com.came.parkare.dashboardapp.ui.screens.settings.components.tabtitle.TabTitle
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.api_label
import dashboardapp.composeapp.generated.resources.auto_brightness_label
import dashboardapp.composeapp.generated.resources.connection_title
import dashboardapp.composeapp.generated.resources.connection_way_label
import dashboardapp.composeapp.generated.resources.delay_brightness_label
import dashboardapp.composeapp.generated.resources.delay_time_label
import dashboardapp.composeapp.generated.resources.discard_button
import dashboardapp.composeapp.generated.resources.ic_close
import dashboardapp.composeapp.generated.resources.image_resources_label
import dashboardapp.composeapp.generated.resources.port_label
import dashboardapp.composeapp.generated.resources.reset_counter_label
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.show_car_counter_label
import dashboardapp.composeapp.generated.resources.show_video_frame_label
import dashboardapp.composeapp.generated.resources.terminal_ip_label
import dashboardapp.composeapp.generated.resources.text_size_scale_label
import dashboardapp.composeapp.generated.resources.upload_file_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ConnectionTab() {
    val viewModel: ConnectionViewModel = koinViewModel()
    viewModel.initTab()
    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp).verticalScroll(ScrollState(0))) {
        TabTitle(Res.string.connection_title)
        Column {
            AppLabel(Res.string.connection_way_label)
            ConnectionWayControl()//connection way
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TerminalIpField()
            PortField()
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ApiField()
            DelayField()
        }
        TextSizeScaleField()
        ShowBrightnessMode()
        ShowCarCounter()
        ShowVideoFrame()
        UploadImages()
        LoadPreviousImages()
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier) {
            SaveButton(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }

}

@Composable
private fun TextSizeScaleField() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    TextField(//text size scale
        value = "${state.textSizeScale}",
        onValueChange ={
            val newValue = if(it.isBlank()) 0 else it.toInt()
            viewModel.textSizeScale(newValue)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        label = { AppLabel(Res.string.text_size_scale_label) }
    )
}

@Composable
private fun DelayField() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    TextField(//delay
        value = "${state.delayTime}",
        onValueChange ={
            val newValue = if(it.isBlank()) 0 else it.toInt()
            viewModel.setDelayTime(newValue = newValue) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        label = { AppLabel(Res.string.delay_time_label) }
    )
}

@Composable
private fun ApiField() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    TextField(//api
        value = state.api,
        onValueChange ={ viewModel.setApi(it) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        label = { AppLabel(Res.string.api_label) }
    )
}

@Composable
private fun PortField() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    TextField(//port
        value = "${state.port}",
        onValueChange ={
            val newValue = if(it.isBlank()) 0 else it.toInt()
            viewModel.setPort(newValue) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        label = { AppLabel(Res.string.port_label) }
    )
}

@Composable
private fun TerminalIpField() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    TextField(//terminal ip
        value = state.terminalIp,
        onValueChange = { viewModel.setTerminalIp(it) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        label = { AppLabel(Res.string.terminal_ip_label) }
    )
}

@Composable
private fun ShowBrightnessMode() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    val brightnessMode: Boolean = state.showBrightnessMode
    val brightnessDelay: Int = state.brightnessDelay
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            stringResource(Res.string.auto_brightness_label)
        )
        Checkbox(
            checked = brightnessMode,
            onCheckedChange = { viewModel.setBrightnessMode(it) }
        )
        if (brightnessMode){
            TextField(//delay
                value = "$brightnessDelay",
                onValueChange ={
                    val newValue = if(it.isBlank()) 0 else it.toInt()
                    viewModel.setBrightnessDelay(newValue)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                label = { AppLabel(Res.string.delay_brightness_label) }
            )
        }
    }
}

@Composable
private fun ShowCarCounter() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    val show: Boolean = state.showCarCounter
    val counter: Int = state.carCounterReset
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            stringResource(Res.string.show_car_counter_label)
        )
        Checkbox(
            checked = show,
            onCheckedChange = { viewModel.setShowCarCounter(it) }
        )
        if (show){
            TextField(//delay
                value = "$counter",
                onValueChange ={
                    val newValue = if(it.isBlank()) 0 else it.toInt()
                    viewModel.setCarCounterReset(newValue)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                label = { AppLabel(Res.string.reset_counter_label) }
            )
        }
    }
}

@Composable
fun LoadPreviousImages() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(items = state.imagesResources){ image ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(100.dp)){
                    Base64Image(base64String = image.fileContentsRaw,
                        modifier = Modifier.fillMaxSize().floatingButton(
                            background = Color.LightGray
                        ),
                        contentScale = ContentScale.FillBounds)

                    DeleteButton(modifier = Modifier
                        .size(24.dp).align(Alignment.TopEnd)){
                        viewModel.removeImage(image)
                    }
                }
                Text(
                    text = image.fileNames,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally).widthIn(max = 80.dp))
            }
        }
    }
}

@Composable
fun DeleteButton(modifier: Modifier, onClick: () -> Unit) {
    IconButton(onClick = { onClick.invoke() },
        modifier = modifier.fillMaxSize(0.82F)) {
        Image(painter = painterResource(Res.drawable.ic_close),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = modifier.background(Color.LightGray).fillMaxSize())
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
private fun SaveButton(modifier: Modifier = Modifier) {
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
fun ConnectionWayControl() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val options: List<String> = state.connectionWayOptions.map { it.second }
    val connectionWay: String = state.connectionWay.second

    CustomDropdownSelector(
        items = options,
        selectedItem = connectionWay,
        onItemSelected = { viewModel.setConnectionWay(it) },
        itemContent = { item ->
            Text(
                text = item,
                fontWeight = if (connectionWay == item) FontWeight.SemiBold else FontWeight.Normal,
                modifier = Modifier.padding(4.dp))
        },
        selectedItemContent = { device ->
            Text(device, fontWeight = FontWeight.Bold)
        }
    )
}

@Composable
private fun ShowVideoFrame() {
    val viewModel: ConnectionViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val showVideo: Boolean = state.showVideoFrame
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

