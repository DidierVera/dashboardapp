@file:OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)

package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.came.parkare.dashboardapp.ui.screens.home.actionbars.BottomBarButtons
import com.came.parkare.dashboardapp.ui.screens.home.actionbars.MainTopBar
import com.came.parkare.dashboardapp.ui.screens.home.blankscreen.BlankScreenTab
import com.came.parkare.dashboardapp.ui.screens.home.configeditor.ConfigEditorScreen
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.DefaultElementsList
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.ElementList
import com.came.parkare.dashboardapp.ui.screens.home.properties.PropertiesEditor
import com.came.parkare.dashboardapp.ui.screens.home.screenlist.ScreenListTab
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreen(onSettingsClick: () -> Unit, onBackClick: () -> Unit ){
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar(onSettingsClick, onBackClick) },
        bottomBar = { BottomBarButtons() },
    ) { padding ->
        Row(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (state.displayDefaultElements || state.displayBlankElements || state.displayDefaultScreens) {
                Box(modifier = Modifier.fillMaxHeight().weight(0.2f)) {
                    ElementList(modifier = Modifier.fillMaxSize())
                    DefaultElementsList(modifier = Modifier.fillMaxSize())
                    ScreenListTab(modifier = Modifier.fillMaxSize())
                }
            }
            if (state.displayBlankScreen) {
                Box(modifier = Modifier.fillMaxHeight().weight(0.2f), contentAlignment = Alignment.Center) {
                    BlankScreenTab(modifier = Modifier.fillMaxSize())
                }
            }
            ConfigEditorScreen(modifier = Modifier.weight(1f).fillMaxHeight())
            if (state.displayProperties) {
                PropertiesEditor(modifier = Modifier.fillMaxHeight().weight(0.2f))
            }
        }
    }
}
