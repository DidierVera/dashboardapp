package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.background_color_label
import dashboardapp.composeapp.generated.resources.binding_section_label
import dashboardapp.composeapp.generated.resources.border_radius_label
import dashboardapp.composeapp.generated.resources.data_key_label
import dashboardapp.composeapp.generated.resources.density_label
import dashboardapp.composeapp.generated.resources.dit_type_code_label
import dashboardapp.composeapp.generated.resources.padding_label
import dashboardapp.composeapp.generated.resources.shadow_label
import dashboardapp.composeapp.generated.resources.style_section_label
import dashboardapp.composeapp.generated.resources.valid_value_label
import org.jetbrains.compose.resources.stringResource

/** Editable copy of the fields that link an element to a DIT value (dataKey, ditTypeCode, validValue). */
@Stable
class BindingDraft(dataKey: String?, ditTypeCode: Int?, validValue: Int?) {
    var dataKey by mutableStateOf(dataKey.orEmpty())
    var ditTypeCode by mutableStateOf(ditTypeCode.asField())
    var validValue by mutableStateOf(validValue.asField())

    val dataKeyValue: String? get() = dataKey.blankToNull()
    val ditTypeCodeValue: Int? get() = ditTypeCode.trim().toIntOrNull()
    val validValueValue: Int? get() = validValue.trim().toIntOrNull()
}

@Composable
fun BindingFields(draft: BindingDraft) {
    PropertySection(stringResource(Res.string.binding_section_label)) {
        PropertyTextField(stringResource(Res.string.data_key_label), draft.dataKey) { draft.dataKey = it }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.dit_type_code_label), draft.ditTypeCode) { draft.ditTypeCode = it }
            }
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.valid_value_label), draft.validValue) { draft.validValue = it }
            }
        }
    }
}

/** Editable copy of the style shared by the box, column and row containers. */
@Stable
class ContainerStyleDraft(
    backgroundColor: String?,
    density: Int,
    roundBorder: Int,
    hasShadow: Boolean,
    padding: Int?
) {
    var backgroundColor by mutableStateOf(backgroundColor.orEmpty())
    var density by mutableStateOf(density.toString())
    var roundBorder by mutableStateOf(roundBorder.toString())
    var hasShadow by mutableStateOf(hasShadow)
    var padding by mutableStateOf(padding.asField())
}

@Composable
fun ContainerStyleFields(draft: ContainerStyleDraft, extraFields: @Composable () -> Unit = {}) {
    PropertySection(stringResource(Res.string.style_section_label)) {
        PropertyColorField(stringResource(Res.string.background_color_label), draft.backgroundColor) { draft.backgroundColor = it }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.density_label) + " (%)", draft.density) { draft.density = it }
            }
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.border_radius_label), draft.roundBorder) { draft.roundBorder = it }
            }
        }
        PropertyNumberField(stringResource(Res.string.padding_label), draft.padding) { draft.padding = it }
        extraFields()
        PropertyCheckbox(stringResource(Res.string.shadow_label), draft.hasShadow) { draft.hasShadow = it }
    }
}

