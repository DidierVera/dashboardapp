@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.no_items_to_show_message
import dashboardapp.composeapp.generated.resources.screen_list_label
import dashboardapp.composeapp.generated.resources.select_screen_message
import dashboardapp.composeapp.generated.resources.send_button
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun TestingTab() {
    val viewModel: TestingViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initConfig()
    }

    val allFieldsFilled = state.selectedScreen != null &&
        state.ditFormGroups.isNotEmpty() &&
        state.ditFormGroups.all { group ->
            group.fields.all { field -> field.value.isNotBlank() }
        }

    Row(modifier = Modifier.fillMaxSize()) {
        ScreenListPanel(
            screens = state.screens,
            selectedScreen = state.selectedScreen,
            onScreenSelect = { viewModel.selectScreen(it) },
        )
        VerticalDivider()
        ScreenDetailPanel(
            selectedScreen = state.selectedScreen,
            ditFormGroups = state.ditFormGroups,
            onFieldUpdate = { type, key, value -> viewModel.updateField(type, key, value) },
            isSending = state.isSending,
            allFieldsFilled = allFieldsFilled,
            onSendClick = { viewModel.sendDitConfig() },
        )
    }
}

@Composable
private fun ScreenListPanel(
    screens: List<ScreenDto>,
    selectedScreen: ScreenDto?,
    onScreenSelect: (ScreenDto) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(8.dp).fillMaxWidth(0.35f),
    ) {
        Text(
            text = stringResource(Res.string.screen_list_label),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
        )
        Box(modifier = Modifier.shadowContainer()) {
            LazyColumn(modifier = Modifier.heightIn(max = 600.dp)) {
                items(screens) { screen ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .floatingButton(
                                isSelected = screen == selectedScreen,
                                onSelectClick = { onScreenSelect(screen) },
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Checkbox(
                            checked = screen == selectedScreen,
                            onCheckedChange = { onScreenSelect(screen) },
                        )
                        Column {
                            Text(
                                text = screen.screenId,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                            )
                            Text(
                                text = "Code: ${screen.dispatchCode}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                            )
                        }
                    }
                }
                if (screens.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(Res.string.no_items_to_show_message),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreenDetailPanel(
    selectedScreen: ScreenDto?,
    ditFormGroups: List<DitFormGroup>,
    onFieldUpdate: (Int, String, String) -> Unit,
    isSending: Boolean,
    allFieldsFilled: Boolean,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
    ) {
        selectedScreen?.let { screen ->
            Text(
                text = screen.screenId,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
                modifier = Modifier.padding(bottom = 8.dp),
            )
            AppButton(
                text = stringResource(Res.string.send_button),
                onClick = onSendClick,
                isEnabled = allFieldsFilled && !isSending,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(ditFormGroups) { group ->
                    DitFormGroupCard(
                        group = group,
                        onFieldUpdate = { key, value -> onFieldUpdate(group.ditTypeCode, key, value) },
                    )
                }
                if (ditFormGroups.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(Res.string.no_items_to_show_message),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(Res.string.select_screen_message),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
            )
        }
    }
}

@Composable
private fun DitFormGroupCard(
    group: DitFormGroup,
    onFieldUpdate: (String, String) -> Unit,
) {
    Column(modifier = Modifier.shadowContainer().padding(8.dp).fillMaxWidth()) {
        Text(
            text = "${group.ditName} (Type ${group.ditTypeCode})",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp),
        )
        group.fields.forEach { field ->
            OutlinedTextField(
                value = field.value,
                onValueChange = { onFieldUpdate(field.key, it) },
                label = { Text(field.key) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            )
        }
    }
}