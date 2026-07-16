@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.blankscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.add_screen_label
import dashboardapp.composeapp.generated.resources.create_new_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun BlankScreenTab(modifier: Modifier = Modifier) {
    val viewModel: BlankScreenViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.widthIn(max = 320.dp).fillMaxSize().floatingButton().padding(8.dp)
    ) {
        Text("New Blank Screen")

        OutlinedTextField(
            value = state.dispatcherCode,
            onValueChange = { viewModel.onDispatcherCode(it) },
            label = { Text("Dispatcher Code") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = state.screenId,
            onValueChange = { viewModel.onScreenId(it) },
            label = { Text("Screen ID") },
            singleLine = true
        )

        OutlinedTextField(
            value = state.marginTop,
            onValueChange = { viewModel.onMarginTop(it) },
            label = { Text("Margin Top") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = state.marginBottom,
            onValueChange = { viewModel.onMarginBottom(it) },
            label = { Text("Margin Bottom") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = state.marginLeft,
            onValueChange = { viewModel.onMarginLeft(it) },
            label = { Text("Margin Left") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = state.marginRight,
            onValueChange = { viewModel.onMarginRight(it) },
            label = { Text("Margin Right") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Button(
            onClick = { viewModel.save() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.add_screen_label))
        }
    }
}
