package com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker

data class FilePickerDialogState(
    val id: Long = 0,
    val pickerVisible: Boolean = false,
    val fileSelected: Boolean = false,
    val fileNames: String = "",
    val fileContents: String = "",
    val fileContentsRaw: String = ""
)
