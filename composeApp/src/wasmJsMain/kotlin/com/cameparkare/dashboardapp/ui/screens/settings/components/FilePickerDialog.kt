package com.cameparkare.dashboardapp.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.components.FilePicker
import com.cameparkare.dashboardapp.ui.screens.settings.components.states.FilePickerDialogState
import com.cameparkare.dashboardapp.ui.screens.settings.components.viewmodels.FilePickerDialogViewModel
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
    buttonText: StringResource = Res.string.upload_file_button,
    buttonIcon: DrawableResource = Res.drawable.ic_import_export,
    onFileSelected: (FilePickerDialogState) -> Unit
){

    val filePickerViewModel: FilePickerDialogViewModel = koinInject()
    val filePickerState = filePickerViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(
            onClick = {
                filePickerViewModel.setPickerVisible(true)
            }
        ){
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.padding(4.dp)) {
                Text(text = stringResource(buttonText))
                Icon(painter = painterResource(buttonIcon), contentDescription = null)
            }
        }
        Column {
            for (fileName in filePickerState.value.fileNames) {
                Text(text = fileName)
            }
        }
    }



    FilePicker(
        show = filePickerState.value.pickerVisible,
        fileExtensions = listOf("json", "png", "jpg")
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