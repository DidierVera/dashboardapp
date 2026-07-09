package com.came.parkare.dashboardapp.ui.components

import androidx.compose.runtime.Composable

actual class PlatformFile

@Composable
actual fun FilePicker(
    show: Boolean,
    initialDirectory: String?,
    fileExtensions: List<String>,
    title: String?,
    onFileSelected: FileSelected,
) {
    // Windows file picker not yet implemented
}

@Composable
actual fun MultipleFilePicker(
    show: Boolean,
    initialDirectory: String?,
    fileExtensions: List<String>,
    title: String?,
    onFileSelected: FilesSelected
) {
    // Windows file picker not yet implemented
}
