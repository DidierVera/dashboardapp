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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.BoxDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun BoxProperties(
    box: BoxDataDto,
    onUpdate: (ElementDto) -> Unit
) {

val viewModel: PropertiesViewModel = koinViewModel()

    var backgroundColor by remember { mutableStateOf(box.backgroundColor ?: "") }
    var density by remember { mutableStateOf(box.density.toString()) }
    var roundBorder by remember { mutableStateOf(box.roundBorder.toString()) }
    var hasShadow by remember { mutableStateOf(box.hasShadow) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = backgroundColor,
            onValueChange = { backgroundColor = it },
            label = { Text("Background Color") }
        )

        OutlinedTextField(
            value = density,
            onValueChange = { density = it },
            label = { Text("Density") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = roundBorder,
            onValueChange = { roundBorder = it },
            label = { Text("Round Border") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = hasShadow,
                onCheckedChange = { hasShadow = it }
            )
            Text("Has Shadow")
        }

        Button(
            onClick = {
                val updatedBox = ElementDto.BoxDto(
                    elementType = "box",
                    data = box.copy(
                    backgroundColor = backgroundColor,
                    density = density.toIntOrNull() ?: 0,
                    roundBorder = roundBorder.toIntOrNull() ?: 0,
                    hasShadow = hasShadow
                ))
                onUpdate( updatedBox)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Changes")
        }
    }
}