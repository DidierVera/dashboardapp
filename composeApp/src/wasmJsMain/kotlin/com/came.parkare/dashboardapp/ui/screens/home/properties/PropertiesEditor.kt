@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.properties

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.properties.ElementEditorPanel
import com.came.parkare.dashboardapp.ui.screens.home.properties.viewmodels.PropertiesViewModel
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.select_element_message
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun PropertiesEditor(
    modifier: Modifier = Modifier
){
    val viewModel: PropertiesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    if(state.showTab){
        Box(modifier = modifier.fillMaxSize().floatingButton().padding(8.dp)) {
            if (state.elementJsonCode.isEmpty()) {
                Text(stringResource(Res.string.select_element_message), modifier = Modifier.align(Alignment.Center))
            } else {
                ElementEditorPanel(
                    jsonCode = state.elementJsonCode,
                    selectionKey = state.location,
                    onJsonChange = { viewModel.setJsonCode(it) },
                    onElementChange = { viewModel.setNewValues(it) },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
