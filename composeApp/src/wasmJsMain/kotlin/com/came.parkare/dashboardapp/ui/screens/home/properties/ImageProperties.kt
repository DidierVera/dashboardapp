@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ImageDataDto
import com.came.parkare.dashboardapp.ui.screens.home.properties.viewmodels.PropertiesViewModel
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.apply_changes_button
import dashboardapp.composeapp.generated.resources.default_text_label
import dashboardapp.composeapp.generated.resources.file_name_label
import dashboardapp.composeapp.generated.resources.font_weight_label
import dashboardapp.composeapp.generated.resources.height_label
import dashboardapp.composeapp.generated.resources.interval_time_label
import dashboardapp.composeapp.generated.resources.item_id_label
import dashboardapp.composeapp.generated.resources.local_file_path_label
import dashboardapp.composeapp.generated.resources.padding_label
import dashboardapp.composeapp.generated.resources.shadow_label
import dashboardapp.composeapp.generated.resources.text_color_label
import dashboardapp.composeapp.generated.resources.text_size_label
import dashboardapp.composeapp.generated.resources.translations_label
import dashboardapp.composeapp.generated.resources.width_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun ImageProperties(
    image: ImageDataDto,
    onUpdate: (ElementDto) -> Unit){

    val viewModel: PropertiesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    var height by mutableStateOf(image.height.toString())
    var width by mutableStateOf(image.width.toString())
    var fileName by mutableStateOf(image.fileName.orEmpty())
    var folderPath by mutableStateOf(image.localFilePath.orEmpty())
    var interval by mutableStateOf((image.intervalTime ?: 3).toString())
    var itemId by mutableStateOf(image.dashboardItemId)
    var multipleItems by mutableStateOf(false)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = fileName,
            onValueChange = { fileName = it },
            label = { Text(stringResource(Res.string.file_name_label)) }
        )

        OutlinedTextField(
            value = itemId,
            onValueChange = { itemId = it },
            label = { Text(stringResource(Res.string.item_id_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = width,
            onValueChange = { width = it },
            label = { Text(stringResource(Res.string.width_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text(stringResource(Res.string.height_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = multipleItems,
                onCheckedChange = { multipleItems = it }
            )
            Text("Multiple items")
        }
        if (multipleItems){
            OutlinedTextField(
                value = folderPath,
                onValueChange = { folderPath = it },
                label = { Text(stringResource(Res.string.local_file_path_label)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = interval,
                onValueChange = { interval = it },
                label = { Text(stringResource(Res.string.interval_time_label)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Button(
            onClick = {
                val updatedBox = ElementDto.ImageDto(
                    elementType = "image",
                    data = image.copy(
                        fileName  = fileName,
                        localFilePath = folderPath,
                        width = width.toIntOrNull() ?: 0,
                        height = height.toIntOrNull() ?: 0,
                        intervalTime = interval.toIntOrNull() ?: 0,
                        dashboardItemId = itemId
                    ))
                onUpdate( updatedBox)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.apply_changes_button))
        }
    }
}