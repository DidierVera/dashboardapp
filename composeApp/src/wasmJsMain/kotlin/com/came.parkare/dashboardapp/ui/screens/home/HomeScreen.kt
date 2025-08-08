@file:OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)

package com.came.parkare.dashboardapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.came.parkare.dashboardapp.ui.screens.home.actionbars.BottomBarButtons
import com.came.parkare.dashboardapp.ui.screens.home.actionbars.MainTopBar
import com.came.parkare.dashboardapp.ui.screens.home.configeditor.ConfigEditorScreen
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.DefaultElementsList
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.ElementList
import com.came.parkare.dashboardapp.ui.screens.home.initmodal.InitModalView
import com.came.parkare.dashboardapp.ui.screens.home.properties.PropertiesEditor
import com.came.parkare.dashboardapp.ui.screens.home.screenlist.ScreenListTab
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreen(onSettingsClick: () -> Unit){
/*
    viewModel.showRequestLogin(message){
        onSettingsClick.invoke()
    }
*/
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar(onSettingsClick) },
        bottomBar = { BottomBarButtons() },
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            ConfigEditorScreen(modifier = Modifier.align(Alignment.Center))
            ElementList(modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight())
            ScreenListTab(modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight())
            DefaultElementsList(modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight())
            PropertiesEditor(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())
            //DefaultScreens(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())

        }
    }
}