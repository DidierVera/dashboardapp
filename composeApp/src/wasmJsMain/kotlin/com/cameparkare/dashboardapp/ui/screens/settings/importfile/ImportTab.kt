package com.cameparkare.dashboardapp.ui.screens.settings.importfile

import androidx.compose.foundation.background
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
import com.cameparkare.dashboardapp.ui.components.AppButton
import com.cameparkare.dashboardapp.ui.screens.settings.components.DialogPickerDialog
import com.cameparkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.cameparkare.dashboardapp.ui.theme.CameBlueColor
import com.cameparkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.file_content_label
import dashboardapp.composeapp.generated.resources.import_export_title
import dashboardapp.composeapp.generated.resources.save_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject


@Composable
fun ImportTab() {
    val viewModel: ImportViewModel = koinInject()
    val state = viewModel.state.collectAsState()

    Column {
        TabTitle(Res.string.import_export_title)
        ButtonOptions(viewModel)

        LazyColumn(userScrollEnabled = true) {
            item { EditorTitle() }
            item { EditorField(viewModel, state)}
        }
    }
}

@Composable
fun EditorField(viewModel: ImportViewModel, state: State<ImportState>) {
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
private fun ButtonOptions(viewModel: ImportViewModel) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            DialogPickerDialog { name, content ->
                viewModel.setValues(name, fileContent = content)
            }
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
