@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.components.DeviceListView
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.import_title
import dashboardapp.composeapp.generated.resources.share_config_option
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ShareConfigTab() {
    val viewModel: ShareConfigViewModel = koinViewModel()

    viewModel.initConfig()

    Column(modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.share_config_option)
        Column {
            Text(text = "Device list")
            DeviceListView()
        }
    }
}