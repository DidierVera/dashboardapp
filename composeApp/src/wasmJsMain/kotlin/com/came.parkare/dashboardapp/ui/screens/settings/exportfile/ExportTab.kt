@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.exportfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.CustomDropdownSelector
import com.came.parkare.dashboardapp.ui.screens.settings.components.tabtitle.TabTitle
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.download_file_button
import dashboardapp.composeapp.generated.resources.file_content_label
import dashboardapp.composeapp.generated.resources.export_title
import dashboardapp.composeapp.generated.resources.ic_download
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ExportTab(){
    val viewModel: ExportViewModel = koinViewModel()

    viewModel.initConfig()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.export_title)


        DownloadButton()

        LazyColumn(userScrollEnabled = true) {
            item { EditorTitle() }
            item { EditorField() }
        }
    }
}

@Composable
private fun DownloadButton() {

    val viewModel: ExportViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
        Button(onClick = { viewModel.downloadFile(state.contentFile) }, modifier = Modifier.padding(8.dp)){
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(4.dp)) {
                Text(text = stringResource(Res.string.download_file_button))
                Icon(painter = painterResource(Res.drawable.ic_download), modifier = Modifier.size(32.dp), contentDescription = null)
            }
        }
    }
}

@Composable
private fun EditorField() {
    val viewModel: ExportViewModel = koinViewModel()
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
            readOnly = true,
            value = state.contentFile, onValueChange = {
                viewModel.setValues(it)
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