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
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ImageDataDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.content_section_label
import dashboardapp.composeapp.generated.resources.file_name_label
import dashboardapp.composeapp.generated.resources.height_label
import dashboardapp.composeapp.generated.resources.interval_time_label
import dashboardapp.composeapp.generated.resources.item_id_label
import dashboardapp.composeapp.generated.resources.local_file_path_label
import dashboardapp.composeapp.generated.resources.multiple_items_label
import dashboardapp.composeapp.generated.resources.style_section_label
import dashboardapp.composeapp.generated.resources.width_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun ImageProperties(
    image: ImageDataDto,
    onUpdate: (ElementDto) -> Unit
) {
    var itemId by remember(image) { mutableStateOf(image.dashboardItemId) }
    var fileName by remember(image) { mutableStateOf(image.fileName.orEmpty()) }
    var width by remember(image) { mutableStateOf(image.width.asField()) }
    var height by remember(image) { mutableStateOf(image.height.asField()) }
    var multipleItems by remember(image) { mutableStateOf(!image.localFilePath.isNullOrBlank()) }
    var folderPath by remember(image) { mutableStateOf(image.localFilePath.orEmpty()) }
    var interval by remember(image) { mutableStateOf(image.intervalTime.asField()) }
    val binding = remember(image) { BindingDraft(image.dataKey, image.ditTypeCode, image.validValue) }

    PropertySection(stringResource(Res.string.content_section_label)) {
        PropertyTextField(stringResource(Res.string.item_id_label), itemId) { itemId = it }
        PropertyTextField(stringResource(Res.string.file_name_label), fileName) { fileName = it }
        PropertyCheckbox(stringResource(Res.string.multiple_items_label), multipleItems) { multipleItems = it }
        if (multipleItems) {
            PropertyTextField(stringResource(Res.string.local_file_path_label), folderPath) { folderPath = it }
            PropertyNumberField(stringResource(Res.string.interval_time_label), interval) { interval = it }
        }
    }

    PropertySection(stringResource(Res.string.style_section_label)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.width_label), width) { width = it }
            }
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.height_label), height) { height = it }
            }
        }
    }

    BindingFields(binding)

    ApplyChangesButton {
        onUpdate(
            ElementDto.ImageDto(
                elementType = "image",
                data = image.copy(
                    dashboardItemId = itemId.trim(),
                    fileName = fileName.blankToNull(),
                    width = width.trim().toIntOrNull(),
                    height = height.trim().toIntOrNull(),
                    localFilePath = if (multipleItems) folderPath.blankToNull() else null,
                    intervalTime = if (multipleItems) interval.trim().toIntOrNull() else null,
                    dataKey = binding.dataKeyValue,
                    ditTypeCode = binding.ditTypeCodeValue,
                    validValue = binding.validValueValue
                )
            )
        )
    }
}
