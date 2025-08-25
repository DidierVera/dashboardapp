package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class ResourceUtilsImpl: ResourceUtils {

    private val _textSizeScale: MutableStateFlow<Int> = MutableStateFlow(10)
    private val _editableTemplate: MutableStateFlow<ConfigTemplateModel> = MutableStateFlow(
        ConfigTemplateModel(templateName = "", screens = emptyList()))
    private val _imagesResource: MutableStateFlow<List<ImagesFileModel>> = MutableStateFlow(emptyList())
    private val _editingElement: MutableStateFlow<String> = MutableStateFlow("")

    override val imagesSource: StateFlow<List<ImagesFileModel>>
        get() = _imagesResource.asStateFlow()

    override val textSizeScale: StateFlow<Int>
        get() = _textSizeScale.asStateFlow()

    override val editableTemplate: StateFlow<ConfigTemplateModel>
        get() = _editableTemplate.asStateFlow()

    override val editingElement: StateFlow<String>
        get() = _editingElement.asStateFlow()

    override fun setImagesSource(images: List<ImagesFileModel>) {
        _imagesResource.update { images }
    }

    override fun setTextSizeScale(value: Int) {
        _textSizeScale.update { value }
    }

    override fun setEditableTemplate(model: ConfigTemplateModel) {
        _editableTemplate.update { model }
        val firstScreen = model.screens.firstOrNull()?.toDto()
        val firstElement = firstScreen?.data?.firstOrNull()
        if (firstElement != null)
            setEditingElement(Json.encodeToString(firstElement))
    }

    override fun setEditingElement(model: String) {
        _editingElement.update { model }
    }
}