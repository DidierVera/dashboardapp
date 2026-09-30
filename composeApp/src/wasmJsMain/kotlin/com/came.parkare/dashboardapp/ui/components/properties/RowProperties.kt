package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.RowDataDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.spacing_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun RowProperties(
    row: RowDataDto,
    onUpdate: (ElementDto) -> Unit,
) {
    val style = remember(row) {
        ContainerStyleDraft(row.backgroundColor, row.density, row.roundBorder, row.hasShadow, row.padding)
    }
    val binding = remember(row) { BindingDraft(row.dataKey, row.ditTypeCode, row.validValue) }
    var spacing by remember(row) { mutableStateOf(row.spacing.toString()) }

    ContainerStyleFields(style) {
        PropertyNumberField(stringResource(Res.string.spacing_label), spacing) { spacing = it }
    }
    BindingFields(binding)

    ApplyChangesButton {
        onUpdate(
            ElementDto.RowDto(
                elementType = "row",
                data = row.copy(
                    backgroundColor = style.backgroundColor.blankToNull(),
                    density = style.density.toIntOrDefault(row.density),
                    roundBorder = style.roundBorder.toIntOrDefault(row.roundBorder),
                    hasShadow = style.hasShadow,
                    padding = style.padding.trim().toIntOrNull(),
                    spacing = spacing.toIntOrDefault(row.spacing),
                    dataKey = binding.dataKeyValue,
                    ditTypeCode = binding.ditTypeCodeValue,
                    validValue = binding.validValueValue
                )
            )
        )
    }
}
