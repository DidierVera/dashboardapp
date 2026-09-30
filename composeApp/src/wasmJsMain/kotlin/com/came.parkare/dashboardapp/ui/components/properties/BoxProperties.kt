package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.BoxDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.height_label
import dashboardapp.composeapp.generated.resources.margin_label
import dashboardapp.composeapp.generated.resources.width_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun BoxProperties(
    box: BoxDataDto,
    onUpdate: (ElementDto) -> Unit
) {
    val style = remember(box) {
        ContainerStyleDraft(box.backgroundColor, box.density, box.roundBorder, box.hasShadow, box.padding)
    }
    val binding = remember(box) { BindingDraft(box.dataKey, box.ditTypeCode, box.validValue) }
    var width by remember(box) { mutableStateOf(box.width.asField()) }
    var height by remember(box) { mutableStateOf(box.height.asField()) }
    var margin by remember(box) { mutableStateOf(box.margin.asField()) }

    ContainerStyleFields(style) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.width_label), width) { width = it }
            }
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.height_label), height) { height = it }
            }
        }
        PropertyNumberField(stringResource(Res.string.margin_label), margin) { margin = it }
    }
    BindingFields(binding)

    ApplyChangesButton {
        onUpdate(
            ElementDto.BoxDto(
                elementType = "box",
                data = box.copy(
                    backgroundColor = style.backgroundColor.blankToNull(),
                    density = style.density.toIntOrDefault(box.density),
                    roundBorder = style.roundBorder.toIntOrDefault(box.roundBorder),
                    hasShadow = style.hasShadow,
                    padding = style.padding.trim().toIntOrNull(),
                    width = width.trim().toIntOrNull(),
                    height = height.trim().toIntOrNull(),
                    margin = margin.trim().toIntOrNull(),
                    dataKey = binding.dataKeyValue,
                    ditTypeCode = binding.ditTypeCodeValue,
                    validValue = binding.validValueValue
                )
            )
        )
    }
}
