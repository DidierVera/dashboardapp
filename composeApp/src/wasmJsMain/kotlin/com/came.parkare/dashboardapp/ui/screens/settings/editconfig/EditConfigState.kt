package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto

data class EditConfigState(
    val elementJsonCode: String = "",
    val screens: List<ScreenDto> = emptyList(),
    val selectedElement: ElementDto? = null,
    val elementPosition: Int? = null,
    val screenViewer: String? = null,
    val textSizeScale: Int = 10,
    val elementsByScreen: List<ElementModel> = emptyList(),
    val imagesSource:List<ImagesFileModel> = emptyList()
)
