@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.AppLabel
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.connection_title
import dashboardapp.composeapp.generated.resources.custom_name_label
import dashboardapp.composeapp.generated.resources.dashboard_ip_label
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.port_label
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.terminal_ip_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun DashboardListTab() {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.dashboard_list_option)
        Spacer(modifier = Modifier.height(12.dp))

        AddNewItem()

        DeviceList()


    }
}

@Composable
fun DeviceList() {
    val viewModel: DashboardListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LazyColumn {
        item {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(Res.string.custom_name_label),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text = stringResource(Res.string.dashboard_ip_label),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }
        }
        items(items = state.currentItems){ device ->
            DeviceItem(device)
        }
    }
}

@Composable
fun DeviceItem(device: DashboardListState) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = device.customName ?: "sin nombre")
        Text(text = device.dashboardIp)
    }
}

@Composable
fun AddNewItem() {
    val viewModel: DashboardListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(//Dashboard ip
                value = state.dashboardIp,
                onValueChange ={ viewModel.setDashboardIp(it) },
                label = { AppLabel(Res.string.dashboard_ip_label) }
            )

            TextField(//custom name
                value = state.customName.orEmpty(),
                onValueChange = { viewModel.setCustomName(it) },
                label = { AppLabel(Res.string.custom_name_label) }
            )
            TextField(//terminal ip
                value = state.terminalIp.orEmpty(),
                onValueChange ={ viewModel.setTerminalIp(it) },
                label = { AppLabel(Res.string.terminal_ip_label) }
            )
        }

        AppButton(
            text = stringResource(Res.string.save_button),
            onClick = {
                viewModel.saveNewItem()
            },
            modifier = Modifier.run {
            if (state.isSaveEnabled) {
                background(CameBlueColor)
            } else {
                background(Color.LightGray)
            }
        })
    }
}
