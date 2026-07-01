package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import kotlinx.coroutines.flow.StateFlow

interface ResourceUtils {
    val imagesSource: StateFlow<List<ResourceFileModel>>
    val textSizeScale: StateFlow<Int>
    val editableTemplate: StateFlow<ConfigTemplateModel>
    val editingElement: StateFlow<String>

    fun setImagesSource(images: List<ResourceFileModel>)
    fun setTextSizeScale(value: Int)
    fun setEditableTemplate(model: ConfigTemplateModel)
    fun setEditingElement(model: String)
}