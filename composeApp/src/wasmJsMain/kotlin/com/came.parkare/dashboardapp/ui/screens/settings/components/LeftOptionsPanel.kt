@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.ui.components.CustomDropdownSelector
import com.came.parkare.dashboardapp.ui.screens.settings.MenuOptionState
import com.came.parkare.dashboardapp.ui.screens.settings.SettingViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.SettingsState
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import kotlinx.browser.document
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun LeftOptionsPanel(
    ipAddress: String,
    options : List<MenuOptionState>,
    onIpAddressClicked: (String) -> Unit,
    onSelectClick: (MenuOptionState) -> Unit){
    val viewModel: SettingViewModel = koinViewModel()
    val ipAddresses by viewModel.ipAddresses.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.width(220.dp).fillMaxHeight().padding(8.dp).shadow(elevation = 4.dp)
        .background(
            color = MaterialTheme.colorScheme.surface
        ).padding(0.dp, 2.dp, 8.dp, 2.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // List of available screens
            LazyColumn(modifier = Modifier.weight(1f))
            {
                items(options) { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                            .floatingButton(option.isSelected){
                                onSelectClick.invoke(option)
                            }
                    ) {
                        Icon(painter = painterResource(option.iconRes),
                            contentDescription = null, modifier = Modifier.size(40.dp))
                        Text(text = stringResource(option.nameRes), fontWeight = if(option.isSelected) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
        }

        CustomDropdownSelector(
            items = ipAddresses,
            selectedItem = ipAddress,
            onItemSelected = {
                viewModel.setIpAddress(it)
            },
            itemContent = {
                IpText(it)
            },
            selectedItemContent = { device ->
                onIpAddressClicked.invoke(device)
                IpText(device)
            }
        )
    }
}

@Composable
private fun IpText(text: String){
    Text(text = text,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(4.dp).fillMaxWidth())

}