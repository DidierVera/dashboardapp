@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.AppLabel
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.custom_name_label
import dashboardapp.composeapp.generated.resources.dashboard_ip_label
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.ic_trash
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.terminal_ip_label
import kotlinx.browser.window
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun DashboardListTab() {
    val viewModel: DashboardListViewModel = koinViewModel()
    viewModel.initTab()
    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.dashboard_list_option)
        AddNewItem()
        DeviceList()
    }
}

@Composable
fun DeviceList() {
    val viewModel: DashboardListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()) {
        LoadHeader(modifier = Modifier)
        HorizontalDivider()
        if(state.currentItems.isEmpty()) EmptyContent()
        else LoadRows(state.currentItems, Modifier)
    }
}

@Composable
fun EmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically),
        modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.LightGray)) {
        Text(text = "There are no items to show", modifier = Modifier.padding(16.dp))
        Box(modifier = Modifier.size(100.dp, 1.dp).background(Color.DarkGray).padding(8.dp))
    }
}

@Composable
fun LoadRows(devices: List<DashboardListState>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxWidth().floatingButton()
    ) {
        devices.forEach { device ->
            items(3) { col ->
                when (col) {
                    0 -> LoadCell(device.customName)
                    1 -> LoadCell(device.dashboardIp)
                    2 -> LastColumn(device.terminalIp, device)
                }
            }
        }
    }
}

@Composable
fun LastColumn(terminalIp: String?, device: DashboardListState) {
    val viewModel: DashboardListViewModel = koinViewModel()
    Row {
        LoadCell(terminalIp)
        if (device.dashboardIp != window.location.hostname){
            IconButton(onClick = {
                viewModel.removeItem(device)
            }){
                Icon(
                    painter = painterResource(Res.drawable.ic_trash),
                    contentDescription = null, )
            }
        }
    }
}

@Composable
fun LoadCell(text: String?) {
    Column(modifier = Modifier.padding(0.dp, 8.dp)) {
        Text(text = text ?: "---- ----", style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(4.dp))
        Box(modifier = Modifier.background(Color.LightGray).fillMaxWidth(0.9F).height(1.dp))
    }
}

@Composable
fun LoadHeader(modifier: Modifier = Modifier) {
    //header
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxWidth()
    ) {
        items(3) { col ->
            when (col) {
                0 -> Text(text = stringResource(Res.string.custom_name_label),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))

                1 -> Text(text = stringResource(Res.string.dashboard_ip_label),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))

                2 -> Text(text = stringResource(Res.string.terminal_ip_label),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun AddNewItem() {
    val viewModel: DashboardListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(//Dashboard ip
                value = state.dashboardIp,
                onValueChange ={ viewModel.setDashboardIp(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                label = { AppLabel(Res.string.dashboard_ip_label) }
            )

            TextField(//custom name
                value = state.customName.orEmpty(),
                onValueChange = { viewModel.setCustomName(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                label = { AppLabel(Res.string.custom_name_label) }
            )
            TextField(//terminal ip
                value = state.terminalIp.orEmpty(),
                onValueChange ={ viewModel.setTerminalIp(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                label = { AppLabel(Res.string.terminal_ip_label) }
            )
        }

        AppButton(
            text = stringResource(Res.string.save_button),
            onClick = {
                viewModel.saveNewItem()
            },
            isEnabled = state.isSaveEnabled
        )
    }
}
