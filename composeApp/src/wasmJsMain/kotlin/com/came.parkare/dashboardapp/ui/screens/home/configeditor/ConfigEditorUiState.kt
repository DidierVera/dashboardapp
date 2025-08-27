package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

data class ConfigEditorUiState (

    val textSizeScale: Int = 10,
    val elementsByScreen: List<ElementModel> = emptyList(),
    val imagesSource:List<ImagesFileModel> = emptyList(),
    val screenViewer: String? = null,
    val editingTemplate: ConfigTemplateModel =
        ConfigTemplateModel(templateName = "blank template", screens = emptyList())
)

