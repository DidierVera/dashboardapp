@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.importfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.CustomDropdownSelector
import com.came.parkare.dashboardapp.ui.screens.settings.components.DialogPickerDialog
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.default_template_label
import dashboardapp.composeapp.generated.resources.file_content_label
import dashboardapp.composeapp.generated.resources.ic_upload
import dashboardapp.composeapp.generated.resources.import_title
import dashboardapp.composeapp.generated.resources.save_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun ImportTab() {
    val viewModel: ImportViewModel = koinViewModel()

    viewModel.initConfig()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.import_title)
        ButtonOptions()

        LazyColumn(userScrollEnabled = true) {
            item { EditorTitle() }
            item { EditorField()}
        }
    }
}

@Composable
private fun EditorField() {
    val viewModel: ImportViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxWidth()
        .padding(4.dp) // Add padding around each row
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        )
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp)
        )
        .padding(4.dp)){
        TextField(modifier = Modifier.fillMaxWidth(),
            value = state.contentFile, onValueChange = {
                viewModel.setValues(state.fileName, it)
            }
        )
    }
}

@Composable
private fun EditorTitle(){
    Text(
        text = stringResource(Res.string.file_content_label),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium)
}

@Composable
private fun ButtonOptions() {
    val viewModel: ImportViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(Res.string.default_template_label)}:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold)
            CustomDropdownSelector(
                items = state.templates.keys.map { it },
                selectedItem = state.selectedTemplate,
                onItemSelected = { viewModel.setSelectedTemplate(it) },
                itemContent = { config ->
                    Text(
                        text = config,
                        fontWeight = if(state.selectedTemplate == config) FontWeight.SemiBold else FontWeight.Normal,
                        modifier = Modifier.padding(4.dp))
                },
                selectedItemContent = { device ->
                    Text(device, fontWeight = FontWeight.Bold)
                }
            )
        }

        DialogPickerDialog(
            buttonIcon = Res.drawable.ic_upload
        ) { name, content ->
            viewModel.setValues(name, fileContent = content)
        }

        // Save button
        AppButton(
            text = stringResource(Res.string.save_button),
            modifier = Modifier.align(Alignment.End),
            onClick = {
                viewModel.saveChanges(state.contentFile)
            },
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = CameBlueColor,
                contentColor = WhiteColor
            )
        )
    }
}
