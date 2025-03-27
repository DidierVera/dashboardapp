package com.cameparkare.dashboardapp.ui.screens.settings.components.states

data class FilePickerDialogState(
    val pickerVisible: Boolean = false,
    val fileSelected: Boolean = false,
    val fileNames: String = "",
    val fileContents: String = "",
    val fileContentsRaw: String = ""
)
