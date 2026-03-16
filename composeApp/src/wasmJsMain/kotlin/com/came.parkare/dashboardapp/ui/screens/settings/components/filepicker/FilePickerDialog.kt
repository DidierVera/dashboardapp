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
    style: PickerButtonStyle = PickerButtonStyle.Outlined,
    onFilesSelected: ((List<FilePickerDialogState>) -> Unit)? = null,
    onFileSelected: ((String, String) -> Unit)? = null
) {
    val filePickerViewModel: FilePickerDialogViewModel = koinInject()
    val filePickerState = filePickerViewModel.state.collectAsState()
    val filesState by filePickerViewModel.multipleFiles.collectAsState()
    val scope = rememberCoroutineScope()

    if (clearFiles) filePickerViewModel.clearPickedValues()

    // Botón mejorado
    when (style) {
        PickerButtonStyle.Outlined -> OutlinedButton(
            onClick = { filePickerViewModel.setPickerVisible(true) },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Icon(
                painter = painterResource(buttonIcon),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(buttonText),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        PickerButtonStyle.Filled -> Button(
            onClick = { filePickerViewModel.setPickerVisible(true) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Icon(
                painter = painterResource(buttonIcon),
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(buttonText),
                style = MaterialTheme.typography.labelLarge
            )
        }

        PickerButtonStyle.Tonal -> FilledTonalButton(
            onClick = { filePickerViewModel.setPickerVisible(true) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            elevation = ButtonDefaults.filledTonalButtonElevation(defaultElevation = 0.dp)
        ) {
            Icon(
                painter = painterResource(buttonIcon),
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(buttonText),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }

    // Tu lógica de pickers — sin cambios
    when (multipleFiles) {
        true -> MultipleFilePicker(
            show = filePickerState.value.pickerVisible,
            fileExtensions = listOf("png", "jpg", "ttf")
        ) {
            filePickerViewModel.setPickerVisible(false)
            filePickerViewModel.clearPickedValues()
            if (it != null) {
                filePickerViewModel.setIsFileSelected(true)
                scope.launch { filePickerViewModel.addFiles(it) }
            } else {
                filePickerViewModel.setIsFileSelected(false)
                filePickerViewModel.setPickerVisible(false)
            }
        }

        false -> FilePicker(
            show = filePickerState.value.pickerVisible,
            fileExtensions = listOf("json")
        ) {
            filePickerViewModel.setPickerVisible(false)
            filePickerViewModel.clearPickedValues()
            if (it != null) {
                filePickerViewModel.setIsFileSelected(true)
                scope.launch { filePickerViewModel.addFile(it) }
            } else {
                filePickerViewModel.setIsFileSelected(false)
                filePickerViewModel.setPickerVisible(false)
            }
        }
    }

    if (filesState.isNotEmpty()) {
        onFilesSelected?.invoke(filesState)
    } else if (filePickerState.value.fileContents.isNotBlank()) {
        onFileSelected?.invoke(
            filePickerState.value.fileNames,
            filePickerState.value.fileContents
        )
    }
}

enum class PickerButtonStyle { Outlined, Filled, Tonal }