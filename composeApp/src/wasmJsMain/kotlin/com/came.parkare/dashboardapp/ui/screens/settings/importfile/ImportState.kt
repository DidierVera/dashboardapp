package com.came.parkare.dashboardapp.ui.screens.settings.importfile

data class ImportState(
    val isLoading: Boolean = false,
    val contentFile: String = "",
    val templates: Map<String, String> = emptyMap(),
    val selectedTemplate: String = "",
    val fileName: String = ""
)
