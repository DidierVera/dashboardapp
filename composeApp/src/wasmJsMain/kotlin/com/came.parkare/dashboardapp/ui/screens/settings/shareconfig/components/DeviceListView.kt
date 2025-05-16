@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.DeviceItemState
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.ShareConfigViewModel
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.GreenColor
import com.came.parkare.dashboardapp.ui.theme.RedColor
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun DeviceListView(modifier: Modifier = Modifier){
    val viewModel: ShareConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Box(){
        LazyColumn(modifier = modifier.shadowContainer()) {
            items(state.dashboardList){ item ->
                DeviceItem(item)
            }
        }
    }
}

@Composable
private fun DeviceItem(item: DeviceItemState) {
    val viewModel: ShareConfigViewModel = koinViewModel()
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .widthIn(min = 250.dp, max = 300.dp)
            .shadowContainer().padding(8.dp, 4.dp)) {
        Checkbox(
            enabled = item.isActive,
            checked = item.isChecked,
            onCheckedChange = { viewModel.setDeviceChecked(item.dashboardIp, it) }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(horizontalAlignment = Alignment.End) {
                Text(text = item.dashboardIp,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold))
                Text(text = item.dashboardName ?: "------",
                    style = MaterialTheme.typography.bodySmall)
            }
            if(item.isCheckingStatus){
                CircularProgressIndicator(
                    strokeWidth = 1.dp,
                    modifier = Modifier.size(12.dp), color = CameBlueColor)
            }else{
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (item.isActive) GreenColor else RedColor)
                )
            }
        }
    }
}

