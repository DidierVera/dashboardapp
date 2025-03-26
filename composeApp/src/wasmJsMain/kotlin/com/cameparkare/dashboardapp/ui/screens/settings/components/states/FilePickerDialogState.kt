package com.cameparkare.dashboardapp.ui.screens.settings.components.states

data class FilePickerDialogState(
    val pickerVisible: Boolean = false,
    val fileSelected: Boolean = false,
    val fileNames: List<String> = emptyList(),
    val fileContents: List<String> = emptyList(),
    val fileContentsRaw: List<String> = emptyList()
)
