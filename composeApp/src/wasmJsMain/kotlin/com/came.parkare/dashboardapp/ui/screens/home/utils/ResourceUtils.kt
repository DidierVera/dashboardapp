package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.ResourceFileModel
import kotlinx.coroutines.flow.StateFlow

/** Screen and top level position of the element being edited in the editable template. */
data class EditingLocation(val screenId: String, val position: Int)

interface ResourceUtils {
    val imagesSource: StateFlow<List<ResourceFileModel>>
    val textSizeScale: StateFlow<Int>
    val editableTemplate: StateFlow<ConfigTemplateModel>
    val editingElement: StateFlow<String>
    val editingLocation: StateFlow<EditingLocation?>

    fun setImagesSource(images: List<ResourceFileModel>)
    fun setTextSizeScale(value: Int)
    fun setEditableTemplate(model: ConfigTemplateModel, resetEditingElement: Boolean = true)
    fun setEditingElement(model: String, location: EditingLocation? = editingLocation.value)
}
