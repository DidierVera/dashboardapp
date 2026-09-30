package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.SpacerDataDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.style_section_label
import dashboardapp.composeapp.generated.resources.value_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun SpacerProperties(
    spacer: SpacerDataDto,
    onUpdate: (ElementDto) -> Unit
) {
    var value by remember(spacer) { mutableStateOf(spacer.value.toString()) }

    PropertySection(stringResource(Res.string.style_section_label)) {
        PropertyNumberField(stringResource(Res.string.value_label), value) { value = it }
    }

    ApplyChangesButton {
        onUpdate(
            ElementDto.SpacerDto(
                elementType = "spacer",
                data = spacer.copy(value = value.toIntOrDefault(spacer.value))
            )
        )
    }
}
