package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.HeaderColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.general_configuration_title
import dashboardapp.composeapp.generated.resources.settings_button
import org.jetbrains.compose.resources.stringResource
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun MainTopBar(navigateToSettings: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth().background(HeaderColor).padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val devices = listOf("Device 1", "Device 2", "Device 3")
        var selectedDevice by remember { mutableStateOf(devices[0]) }

        Row(modifier = Modifier.size(0.dp)) {
            CustomDropdownSelector(
                items = devices,
                selectedItem = selectedDevice,
                onItemSelected = { selectedDevice = it },
                itemContent = { device ->
                    Row {
                        Icon(Icons.Default.Add, contentDescription = "Device")
                        Spacer(Modifier.width(8.dp))
                        Text(device)
                    }
                },
                selectedItemContent = { device ->
                    Text("Selected: $device", fontWeight = FontWeight.Bold)
                }
            )
            CustomDropdownSelector(
                items = devices,
                selectedItem = selectedDevice,
                onItemSelected = { selectedDevice = it },
                itemContent = { device ->
                    Row {
                        Icon(Icons.Default.Add, contentDescription = "Device")
                        Spacer(Modifier.width(8.dp))
                        Text(device)
                    }
                },
                selectedItemContent = { device ->
                    Text("Selected: $device", fontWeight = FontWeight.Bold)
                }
            )
        }
        Text(text = stringResource(Res.string.general_configuration_title), color = WhiteColor)

        // Settings button
        AppButton(text = stringResource(Res.string.settings_button), onClick = navigateToSettings,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = CameBlueColor,
                contentColor = WhiteColor
            ) )
    }
}