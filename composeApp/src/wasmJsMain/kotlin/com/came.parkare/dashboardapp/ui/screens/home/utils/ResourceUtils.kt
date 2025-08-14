package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import kotlinx.coroutines.flow.StateFlow

interface ResourceUtils {
    val imagesSource: StateFlow<List<ImagesFileModel>>
    val textSizeScale: StateFlow<Int>
    val editableTemplate: StateFlow<ConfigTemplateModel>

    fun setImagesSource(images: List<ImagesFileModel>)
    fun setTextSizeScale(value: Int)
    fun setEditableTemplate(model: ConfigTemplateModel)
}