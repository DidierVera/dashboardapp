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
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ColumnDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.screens.home.properties.viewmodels.PropertiesViewModel
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.apply_changes_button
import dashboardapp.composeapp.generated.resources.background_color_label
import dashboardapp.composeapp.generated.resources.border_radius_label
import dashboardapp.composeapp.generated.resources.density_label
import dashboardapp.composeapp.generated.resources.height_label
import dashboardapp.composeapp.generated.resources.margin_label
import dashboardapp.composeapp.generated.resources.padding_label
import dashboardapp.composeapp.generated.resources.shadow_label
import dashboardapp.composeapp.generated.resources.spacing_label
import dashboardapp.composeapp.generated.resources.width_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ColumnProperties(
    column: ColumnDataDto,
    onUpdate: (ElementDto) -> Unit,
){

    val viewModel: PropertiesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    var backgroundColor by mutableStateOf(column.backgroundColor ?: "")
    var density by mutableStateOf(column.density.toString())
    var spacing by mutableStateOf(column.spacing.toString())
    var padding by mutableStateOf(column.padding.toString())
    var roundBorder by mutableStateOf(column.roundBorder.toString())
    var hasShadow by mutableStateOf(column.hasShadow)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = backgroundColor,
            onValueChange = { backgroundColor = it },
            label = { Text(stringResource(Res.string.background_color_label)) }
        )

        OutlinedTextField(
            value = density,
            onValueChange = { density = it },
            label = { Text(stringResource(Res.string.density_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = roundBorder,
            onValueChange = { roundBorder = it },
            label = { Text(stringResource(Res.string.border_radius_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = spacing,
            onValueChange = { spacing = it },
            label = { Text(stringResource(Res.string.spacing_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = padding,
            onValueChange = { padding = it },
            label = { Text(stringResource(Res.string.padding_label)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = hasShadow,
                onCheckedChange = { hasShadow = it }
            )
            Text(stringResource(Res.string.shadow_label))
        }

        Button(
            onClick = {
                val updatedBox = ElementDto.ColumnDto(
                    elementType = "column",
                    data = column.copy(
                        backgroundColor = backgroundColor,
                        density = density.toIntOrNull() ?: 0,
                        roundBorder = roundBorder.toIntOrNull() ?: 0,
                        hasShadow = hasShadow
                    ))
                onUpdate( updatedBox)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.apply_changes_button))
        }
    }
}