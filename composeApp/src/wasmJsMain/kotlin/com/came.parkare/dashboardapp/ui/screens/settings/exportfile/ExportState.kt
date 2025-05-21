package com.came.parkare.dashboardapp.ui.screens.settings.exportfile

import org.jetbrains.compose.resources.StringResource

data class ExportState(
    val contentFile: String = "",
    val templates: Map<String, String> = emptyMap(),
    val selectedTemplate: String = "",
    val currentConfigLabel: String = "CURRENT CONFIG"
)
