package com.cameparkare.dashboardapp.ui.screens.settings.importexport

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.components.FilePicker
import com.cameparkare.dashboardapp.ui.components.PlatformFile
import com.cameparkare.dashboardapp.ui.components.readFileAsByteArray
import com.cameparkare.dashboardapp.ui.screens.settings.components.DialogPickerDialog
import com.cameparkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.cameparkare.dashboardapp.ui.screens.settings.components.viewmodels.FilePickerDialogViewModel
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.import_export_title
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@Composable
fun ImportExportTab() {
    LazyColumn {
        item {
            TabTitle(Res.string.import_export_title)

            Row(modifier = Modifier.padding(8.dp)) {
                DialogPickerDialog{ itemSelected ->

                }
            }
        }
    }
}