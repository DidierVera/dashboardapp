@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.TextDataDto
import com.came.parkare.dashboardapp.ui.components.CustomDropdownSelector
import com.came.parkare.dashboardapp.ui.components.DropDownControl
import com.came.parkare.dashboardapp.ui.screens.home.properties.viewmodels.PropertiesViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.connection.ConnectionViewModel
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.apply_changes_button
import dashboardapp.composeapp.generated.resources.background_color_label
import dashboardapp.composeapp.generated.resources.border_radius_label
import dashboardapp.composeapp.generated.resources.default_text_label
import dashboardapp.composeapp.generated.resources.density_label
import dashboardapp.composeapp.generated.resources.font_weight_label
import dashboardapp.composeapp.generated.resources.height_label
import dashboardapp.composeapp.generated.resources.item_id_label
import dashboardapp.composeapp.generated.resources.margin_label
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
fun TextProperties(
    text: TextDataDto,
    onUpdate: (ElementDto) -> Unit){

    val viewModel: PropertiesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    var textColor by remember { mutableStateOf(text.textColor) }
    var textSize by remember { mutableStateOf(text.textSize.toString()) }
    var padding by remember { mutableStateOf(text.padding.toString()) }
    val fontWeightList by remember { mutableStateOf(listOf("Bold", "Medium", "Regular")) }
    var fontWeightSelected by remember { mutableStateOf("Regular") }
    var defaultText by remember { mutableStateOf(text.defaultText) }
    var itemId by remember { mutableStateOf(text.dashboardItemId) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = itemId,
            onValueChange = { itemId = it },
            label = { Text(stringResource(Res.string.item_id_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = defaultText,
            onValueChange = { defaultText = it },
            label = { Text(stringResource(Res.string.default_text_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = textColor,
            onValueChange = { textColor = it },
            label = { Text(stringResource(Res.string.text_color_label)) }
        )

        OutlinedTextField(
            value = textSize,
            onValueChange = { textSize = it },
            label = { Text(stringResource(Res.string.text_size_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        DropDownControl(fontWeightList, fontWeightSelected, label = stringResource(Res.string.font_weight_label)){
            fontWeightSelected =  it
        }

        OutlinedTextField(
            value = padding,
            onValueChange = { padding = it },
            label = { Text(stringResource(Res.string.padding_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = itemId,
            onValueChange = { itemId = it },
            label = { Text(stringResource(Res.string.translations_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val updatedBox = ElementDto.TextDto(
                    elementType = "text",
                    data = text.copy(
                        textColor = textColor,
                        textSize = textSize.toIntOrNull() ?: 0,
                        defaultText = defaultText,
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