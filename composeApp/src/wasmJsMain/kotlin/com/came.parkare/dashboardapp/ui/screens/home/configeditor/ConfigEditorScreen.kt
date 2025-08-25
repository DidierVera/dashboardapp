@file:OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class)

package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.background.LoadBackground
import com.came.parkare.dashboardapp.ui.screens.settings.components.BuildElement
import com.came.parkare.dashboardapp.ui.screens.settings.editconfig.EditConfigViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.ShareConfigViewModel
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.screen_preview_label
import dashboardapp.composeapp.generated.resources.select_screen_message
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ConfigEditorScreen(modifier: Modifier = Modifier,
       viewModel: ConfigEditorViewModel = koinViewModel()){
    viewModel.initConfig()


    Box(modifier = modifier.size(750.dp).floatingButton()
        ){
        ElementsList()

    }
}


@Composable
private fun ElementsList(modifier: Modifier = Modifier) {
    val viewModel: ConfigEditorViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if (state.elementsByScreen.isNotEmpty()){
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = stringResource(Res.string.screen_preview_label),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            LazyColumn {
                item {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(4.dp)){
                        val background = state.imagesSource.firstOrNull { it.fileName?.contains("background") == true }
                        LoadBackground(background)
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(12.dp, 0.dp)) {
                            for(i in state.elementsByScreen.indices){
                                val mItem = state.elementsByScreen[i]

                                Box(modifier = Modifier.clickable {
                                    viewModel.selectItemOnScreen(mItem, i)
                                }) {
                                    BuildElement(mItem, state.textSizeScale, state.imagesSource){ element ->
                                        viewModel.selectItemOnScreen(element, i)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    else{
        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center){
            Text(stringResource(Res.string.select_screen_message))
        }
    }
}
