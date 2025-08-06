@file:OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class)

package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ConfigEditorScreen(modifier: Modifier = Modifier,
       viewModel: ConfigEditorViewModel = koinViewModel()){
    viewModel.initConfig()


    Box(modifier = modifier.size(750.dp).floatingButton()
        .verticalScroll(ScrollState(0)).horizontalScroll(ScrollState(0))){


    }
}