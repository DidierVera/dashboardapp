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
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.VideoDataDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.content_section_label
import dashboardapp.composeapp.generated.resources.file_name_label
import dashboardapp.composeapp.generated.resources.height_label
import dashboardapp.composeapp.generated.resources.item_id_label
import dashboardapp.composeapp.generated.resources.local_file_path_label
import dashboardapp.composeapp.generated.resources.style_section_label
import dashboardapp.composeapp.generated.resources.width_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun VideoProperties(
    video: VideoDataDto,
    onUpdate: (ElementDto) -> Unit
) {
    var itemId by remember(video) { mutableStateOf(video.dashboardItemId) }
    var fileName by remember(video) { mutableStateOf(video.fileName.orEmpty()) }
    var folderPath by remember(video) { mutableStateOf(video.localFilePath.orEmpty()) }
    var width by remember(video) { mutableStateOf(video.width.asField()) }
    var height by remember(video) { mutableStateOf(video.height.asField()) }
    val binding = remember(video) { BindingDraft(video.dataKey, video.ditTypeCode, video.validValue) }

    PropertySection(stringResource(Res.string.content_section_label)) {
        PropertyTextField(stringResource(Res.string.item_id_label), itemId) { itemId = it }
        PropertyTextField(stringResource(Res.string.file_name_label), fileName) { fileName = it }
        PropertyTextField(stringResource(Res.string.local_file_path_label), folderPath) { folderPath = it }
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
            ElementDto.VideoDto(
                elementType = "video",
                data = video.copy(
                    dashboardItemId = itemId.trim(),
                    fileName = fileName.blankToNull(),
                    localFilePath = folderPath.blankToNull(),
                    width = width.trim().toIntOrNull(),
                    height = height.trim().toIntOrNull(),
                    dataKey = binding.dataKeyValue,
                    ditTypeCode = binding.ditTypeCodeValue,
                    validValue = binding.validValueValue
                )
            )
        )
    }
}
