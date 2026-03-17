package com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.FilePicker
import com.came.parkare.dashboardapp.ui.components.MultipleFilePicker
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.ic_import_export
import dashboardapp.composeapp.generated.resources.upload_file_button
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
@Composable
fun DialogPickerDialog(
    iconSize: Dp = 18.dp,
    buttonText: StringResource = Res.string.upload_file_button,
    buttonIcon: DrawableResource = Res.drawable.ic_import_export,
    multipleFiles: Boolean = false,
    clearFiles: Boolean = false,
    fileExtensions: List<String> = listOf("png", "jpg"),
    onFilesSelected: ((List<FilePickerDialogState>) -> Unit)? = null,
    onFileSelected: ((String, String) -> Unit)? = null
) {
    val viewModel: FilePickerDialogViewModel = koinInject()
    val pickerState by viewModel.state.collectAsState()
    val filesState  by viewModel.multipleFiles.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(clearFiles) {
        if (clearFiles) viewModel.clearPickedValues()
    }

    PickerOutlinedButton(
        icon = buttonIcon,
        iconSize = iconSize,
        text = buttonText,
        onClick = { viewModel.setPickerVisible(true) }
    )

    FilePickerLauncher(
        show = pickerState.pickerVisible,
        multipleFiles = multipleFiles,
        fileExtensions = fileExtensions,
        onMultipleResult = { files ->
            viewModel.setPickerVisible(false)
            viewModel.clearPickedValues()
            if (files != null) {
                viewModel.setIsFileSelected(true)
                scope.launch { viewModel.addFiles(files) }
            } else {
                viewModel.setIsFileSelected(false)
            }
        },
        onSingleResult = { file ->
            viewModel.setPickerVisible(false)
            viewModel.clearPickedValues()
            if (file != null) {
                viewModel.setIsFileSelected(true)
                scope.launch { viewModel.addFile(file) }
            } else {
                viewModel.setIsFileSelected(false)
            }
        }
    )

    LaunchedEffect(filesState, pickerState.fileContents) {
        if (filesState.isNotEmpty()) {
            onFilesSelected?.invoke(filesState)
        } else if (pickerState.fileContents.isNotBlank()) {
            onFileSelected?.invoke(pickerState.fileNames, pickerState.fileContents)
        }
    }
}