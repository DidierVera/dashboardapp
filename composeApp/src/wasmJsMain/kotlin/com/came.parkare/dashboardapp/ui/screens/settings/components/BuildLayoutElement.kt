package com.came.parkare.dashboardapp.ui.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.components.elements.BuildBoxView
import com.came.parkare.dashboardapp.ui.components.elements.BuildColumnView
import com.came.parkare.dashboardapp.ui.components.elements.BuildRowView
import com.came.parkare.dashboardapp.ui.components.elements.BuildTextView
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.components.LoadElementImage


@Composable
fun BuildElement(element: ElementModel, textSizeScale: Int,
                 imageFiles: List<ImagesFileModel>? = null, onEditingMode: ((ElementModel) -> Unit)? = null){
    val scaleFactor = (textSizeScale / 10f).coerceIn(0.5f, 3f)
    when(element){
        is ElementModel.BoxModel -> {
            val box = element.data
            BuildBoxView(box = box, textSizeScale = textSizeScale, scaleFactor = scaleFactor) { dto, scale ->
                Box(modifier = Modifier.run {
                    if (onEditingMode != null) {
                        clickable { onEditingMode(dto) }
                    } else {
                        this
                    }
                }){
                    BuildElement(dto, scale, imageFiles)
                }
            }
        }
        is ElementModel.ColumnModel -> {
            val column = element.data
            BuildColumnView(column = column, textSizeScale = textSizeScale, scaleFactor = scaleFactor) { dto, scale ->
                Box(modifier = Modifier.run {
                    if (onEditingMode != null) {
                        clickable { onEditingMode(dto) }
                    } else {
                        this
                    }
                }){
                    BuildElement(dto, scale, imageFiles)
                }
            }
        }
        is ElementModel.ImageModel -> {
            val image = element.data
            val file = imageFiles?.firstOrNull { img -> img.fileName?.contains(image.fileName.orEmpty()) == true }

            LoadElementImage(image.copy(folderPath = file?.fileContent), file, scaleFactor)
        }
        is ElementModel.RowModel -> {
            val row = element.data
            BuildRowView(row = row, textSizeScale = textSizeScale, scaleFactor = scaleFactor) { dto, scale ->
                Box(modifier = Modifier.run {
                    if (onEditingMode != null) {
                        clickable { onEditingMode(dto) }
                    } else {
                        this
                    }
                }){
                    BuildElement(dto, scale, imageFiles)
                }
            }
        }
        is ElementModel.SpacerModel -> {
            Spacer(modifier = Modifier.size((element.data.value.toFloat() * scaleFactor).dp))
        }
        is ElementModel.TextModel -> {
            val text = element.data
            BuildTextView(text = text, scaleFactor)
        }
        is ElementModel.VideoModel -> {
            Text(text = "Video")
        }
    }
}