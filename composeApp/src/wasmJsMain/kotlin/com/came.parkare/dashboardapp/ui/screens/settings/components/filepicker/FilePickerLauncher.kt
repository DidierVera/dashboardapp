package com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker

import androidx.compose.runtime.Composable
import com.came.parkare.dashboardapp.ui.components.FilePicker
import com.came.parkare.dashboardapp.ui.components.MultipleFilePicker
import com.came.parkare.dashboardapp.ui.components.PlatformFile


@Composable
fun FilePickerLauncher(
    show: Boolean,
    multipleFiles: Boolean,
    fileExtensions: List<String>,
    onMultipleResult: (List<PlatformFile>?) -> Unit,
    onSingleResult: (PlatformFile?) -> Unit
) {
    if (multipleFiles) {
        MultipleFilePicker(
            show = show,
            fileExtensions = fileExtensions
        ) {
            onMultipleResult(it)
        }
    } else {
        FilePicker(
            show = show,
            fileExtensions = fileExtensions
        ) {
            onSingleResult(it)
        }
    }
}