package com.came.parkare.dashboardapp.ui.screens.home.utils

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ResourceUtilsImpl: ResourceUtils {

    private val _textSizeScale: MutableStateFlow<Int> = MutableStateFlow(10)
    private val _editableTemplate: MutableStateFlow<ConfigTemplateModel> = MutableStateFlow(
        ConfigTemplateModel(templateName = "", screens = emptyList())
    )
    private val _imagesResource: MutableStateFlow<List<ImagesFileModel>> = MutableStateFlow(
        emptyList())

    override val imagesSource: StateFlow<List<ImagesFileModel>>
        get() = _imagesResource.asStateFlow()

    override val textSizeScale: StateFlow<Int>
        get() = _textSizeScale.asStateFlow()

    override val editableTemplate: StateFlow<ConfigTemplateModel>
        get() = _editableTemplate.asStateFlow()

    override fun setImagesSource(images: List<ImagesFileModel>) {
        _imagesResource.update { images }
    }

    override fun setTextSizeScale(value: Int) {
        _textSizeScale.update { value }
    }

    override fun setEditableTemplate(model: ConfigTemplateModel) {
        _editableTemplate.update { model }
    }
}