package com.came.parkare.dashboardapp.ui.screens.home.templates

import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel

data class TemplateState(
    val existingItems: List<ConfigTemplateModel> = emptyList(),
    val defaultTemplates: List<ConfigTemplateModel> = emptyList()

)
