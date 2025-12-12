package com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
    iconSize: Dp = 32.dp,
    buttonText: StringResource = Res.string.upload_file_button,
    buttonIcon: DrawableResource = Res.drawable.ic_import_export,
    multipleFiles: Boolean = false,
    clearFiles: Boolean = false,
    onFilesSelected: ((List<FilePickerDialogState>) -> Unit)? = null,
    onFileSelected: ((String, String) -> Unit)? = null
){

    val filePickerViewModel: FilePickerDialogViewModel = koinInject()
    val filePickerState = filePickerViewModel.state.collectAsState()
    val filesState by filePickerViewModel.multipleFiles.collectAsState()

    val scope = rememberCoroutineScope()

    when(clearFiles){
        true -> filePickerViewModel.clearPickedValues()
        false -> { }
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(
            onClick = {
                filePickerViewModel.setPickerVisible(true)
            }
        ){
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(4.dp)) {
                Text(text = stringResource(buttonText))
                Icon(painter = painterResource(buttonIcon), modifier = Modifier.size(iconSize),  contentDescription = null)
            }
        }
    }

    when (multipleFiles){
        true -> {
            MultipleFilePicker(
                show = filePickerState.value.pickerVisible,
                fileExtensions = listOf("png", "jpg")
            ){
                filePickerViewModel.setPickerVisible(false)
                filePickerViewModel.clearPickedValues()
                if (it != null) {
                    filePickerViewModel.setIsFileSelected(true)
                    scope.launch {
                        filePickerViewModel.addFiles(it)
                    }
                } else {
                    filePickerViewModel.setIsFileSelected(false)
                    filePickerViewModel.setPickerVisible(false)
                }
            }

        }
        false -> {
            FilePicker(
                show = filePickerState.value.pickerVisible,
                fileExtensions = listOf("json")
            ) {
                filePickerViewModel.setPickerVisible(false)
                filePickerViewModel.clearPickedValues()

                if (it != null) {
                    filePickerViewModel.setIsFileSelected(true)
                    scope.launch {
                        filePickerViewModel.addFile(it)
                    }
                } else {
                    filePickerViewModel.setIsFileSelected(false)
                    filePickerViewModel.setPickerVisible(false)
                }
            }
        }
    }

    if (filesState.isNotEmpty()){
        onFilesSelected?.invoke(filesState)
    }else if (filePickerState.value.fileContents.isNotBlank()){
        onFileSelected?.invoke(filePickerState.value.fileNames, filePickerState.value.fileContents)
    }
}