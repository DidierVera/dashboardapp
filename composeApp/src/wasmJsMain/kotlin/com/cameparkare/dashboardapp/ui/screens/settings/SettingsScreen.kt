package com.cameparkare.dashboardapp.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.cameparkare.dashboardapp.ui.screens.settings.components.LeftOptionsPanel
import com.cameparkare.dashboardapp.ui.screens.settings.components.SettingTopBar
import com.cameparkare.dashboardapp.ui.screens.settings.connection.ConnectionTab
import com.cameparkare.dashboardapp.ui.screens.settings.dashboardlist.DashboardListTab
import com.cameparkare.dashboardapp.ui.screens.settings.importexport.ImportExportTab
import com.cameparkare.dashboardapp.ui.screens.settings.testing.TestingTab
import com.cameparkare.dashboardapp.ui.screens.settings.viewmodels.SettingViewModel
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.connection_option
import dashboardapp.composeapp.generated.resources.dashboard_list_option
import dashboardapp.composeapp.generated.resources.import_export_option
import dashboardapp.composeapp.generated.resources.testing_option
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(onBackClick: () -> Unit, onSaveClick: () -> Unit){
    val viewModel: SettingViewModel = koinInject()
    val optionsState = viewModel.optionsState.collectAsState()
    val selectedOption = viewModel.selectedOption.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        SettingTopBar(onBackClick, onSaveClick)

        Row {
            LeftOptionsPanel(optionsState.value){ option ->
                viewModel.selectItem(option)
            }

            //content
            when(selectedOption.value.nameRes){
                Res.string.connection_option -> ConnectionTab()
                Res.string.dashboard_list_option -> DashboardListTab()
                Res.string.import_export_option -> ImportExportTab()
                Res.string.testing_option -> TestingTab()
                else -> {}
            }
        }
    }

}