package com.cameparkare.dashboardapp.ui.screens.settings.importexport

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.components.FilePicker
import com.cameparkare.dashboardapp.ui.components.PlatformFile
import com.cameparkare.dashboardapp.ui.components.readFileAsByteArray
import com.cameparkare.dashboardapp.ui.screens.settings.components.DialogPickerDialog
import com.cameparkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.cameparkare.dashboardapp.ui.screens.settings.components.viewmodels.FilePickerDialogViewModel
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.import_export_title
import dashboardapp.composeapp.generated.resources.save_button
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@Composable
fun ImportExportTab() {
    val viewModel: ImportExportViewModel = koinInject()
    val state = viewModel.state.collectAsState()

    Column {
        TabTitle(Res.string.import_export_title)
        Row(modifier = Modifier.padding(8.dp)) {
            DialogPickerDialog { name, content ->
                viewModel.setValues(name, fileContent = content)
            }
        }
        LazyColumn(userScrollEnabled = true) {
            item {
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
                        value = state.value.contentFile, onValueChange = {
                            viewModel.setValues(state.value.fileName, it)
                        })
                }
            }
        }
    }
}