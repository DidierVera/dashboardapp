@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.came.parkare.dashboardapp.ui.screens.settings.components.LeftOptionsPanel
import com.came.parkare.dashboardapp.ui.screens.settings.components.SettingTopBar
import com.came.parkare.dashboardapp.ui.screens.settings.connection.ConnectionTab
import com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist.DashboardListTab
import com.came.parkare.dashboardapp.ui.screens.settings.editconfig.EditConfigTab
import com.came.parkare.dashboardapp.ui.screens.settings.exportfile.ExportTab
import com.came.parkare.dashboardapp.ui.screens.settings.importfile.ImportTab
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.ShareConfigTab
import com.came.parkare.dashboardapp.ui.screens.settings.testing.TestingTab
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.connection_option
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.edit_current_config_option
import dashboardapp.composeapp.generated.resources.export_option
import dashboardapp.composeapp.generated.resources.import_export_option
import dashboardapp.composeapp.generated.resources.import_option
import dashboardapp.composeapp.generated.resources.share_config_option
import dashboardapp.composeapp.generated.resources.testing_option
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun SettingsScreen(onBackClick: () -> Unit){
    val viewModel: SettingViewModel = koinViewModel()
    val refreshState by viewModel.refreshState.collectAsState()

    key(refreshState){
        val selectedOption by viewModel.selectedOption.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            SettingTopBar(onBackClick)
            Row {
                LeftPanel()

                when(selectedOption.nameRes){
                    Res.string.connection_option -> ConnectionTab()
                    Res.string.dashboard_list_option -> DashboardListTab()
                    Res.string.import_option -> ImportTab()
                    Res.string.export_option -> ExportTab()
                    Res.string.share_config_option -> ShareConfigTab()
                    Res.string.testing_option -> TestingTab()
                    Res.string.edit_current_config_option -> EditConfigTab()
                    else -> {}
                }
            }
        }
    }
}


@Composable
fun LeftPanel() {
    val viewModel: SettingViewModel  = koinViewModel()
    val optionsState by viewModel.optionsState.collectAsState()
    val settingsState by viewModel.settingsState.collectAsState()
    Column {
        LeftOptionsPanel(settingsState.ipSelected, optionsState, onIpAddressClicked = {
        }){ option ->
            viewModel.selectItem(option)
        }
    }
}
