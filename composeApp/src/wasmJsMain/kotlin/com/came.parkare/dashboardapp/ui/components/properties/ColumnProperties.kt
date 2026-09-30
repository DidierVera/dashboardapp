package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ColumnDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.spacing_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun ColumnProperties(
    column: ColumnDataDto,
    onUpdate: (ElementDto) -> Unit,
) {
    val style = remember(column) {
        ContainerStyleDraft(column.backgroundColor, column.density, column.roundBorder, column.hasShadow, column.padding)
    }
    val binding = remember(column) { BindingDraft(column.dataKey, column.ditTypeCode, column.validValue) }
    var spacing by remember(column) { mutableStateOf(column.spacing.toString()) }

    ContainerStyleFields(style) {
        PropertyNumberField(stringResource(Res.string.spacing_label), spacing) { spacing = it }
    }
    BindingFields(binding)

    ApplyChangesButton {
        onUpdate(
            ElementDto.ColumnDto(
                elementType = "column",
                data = column.copy(
                    backgroundColor = style.backgroundColor.blankToNull(),
                    density = style.density.toIntOrDefault(column.density),
                    roundBorder = style.roundBorder.toIntOrDefault(column.roundBorder),
                    hasShadow = style.hasShadow,
                    padding = style.padding.trim().toIntOrNull(),
                    spacing = spacing.toIntOrDefault(column.spacing),
                    dataKey = binding.dataKeyValue,
                    ditTypeCode = binding.ditTypeCodeValue,
                    validValue = binding.validValueValue
                )
            )
        )
    }
}
