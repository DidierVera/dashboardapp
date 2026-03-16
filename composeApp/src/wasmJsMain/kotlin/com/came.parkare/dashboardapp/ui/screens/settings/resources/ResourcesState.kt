package com.came.parkare.dashboardapp.ui.screens.settings.resources

import com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker.FilePickerDialogState

data class ResourcesState(
    val loading: Boolean = false,
    val clearSelectedFiles: Boolean = false,
    val imagesResources: List<FilePickerDialogState> = emptyList()
)
