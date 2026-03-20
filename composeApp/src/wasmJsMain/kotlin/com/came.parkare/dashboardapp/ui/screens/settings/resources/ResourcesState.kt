package com.came.parkare.dashboardapp.ui.screens.settings.resources

import com.came.parkare.dashboardapp.config.constants.Constants.FONT_BOLD
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_MEDIUM
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_REGULAR
import com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker.FilePickerDialogState

data class ResourcesState(
    val loading: Boolean = false,
    val clearSelectedFiles: Boolean = false,
    val imagesResources: List<FilePickerDialogState> = emptyList(),
    val fontResources: List<FilePickerDialogState> = emptyList(),
)


enum class FontWeightType(val fileName: String) {
    REGULAR(FONT_REGULAR),
    MEDIUM(FONT_MEDIUM),
    BOLD(FONT_BOLD)
}