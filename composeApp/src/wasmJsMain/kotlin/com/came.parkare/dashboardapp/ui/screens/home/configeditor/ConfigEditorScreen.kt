@file:OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class,
    KoinExperimentalAPI::class
)

package com.came.parkare.dashboardapp.ui.screens.home.configeditor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import dashboardapp.composeapp.generated.resources.ic_item_arrow
import dashboardapp.composeapp.generated.resources.screen_preview_label
import dashboardapp.composeapp.generated.resources.select_screen_message
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ConfigEditorScreen(modifier: Modifier = Modifier,
       viewModel: ConfigEditorViewModel = koinViewModel()){
    viewModel.initConfig()

    Row(modifier = modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.spacedBy(8.dp)){
        Spacer(modifier = Modifier.weight(0.1f))
        Box(modifier = Modifier.weight(0.3f).floatingButton()) {
            ScreenList()

        }
        Box(modifier = Modifier.weight(0.5f).floatingButton()){
            ElementsList()
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun ScreenList() {
    val viewModel: ConfigEditorViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = Modifier.heightIn(max = 600.dp)) {
        items(state.editingTemplate.screens){ screen ->
            Row(modifier = Modifier
                .widthIn(min = 450.dp)
                .floatingButton(
                    isSelected = screen.screenId == state.screenViewer,
                    onSelectClick = {
                        viewModel.selectScreen(screen) })
                .padding(8.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    painter = painterResource(Res.drawable.ic_item_arrow),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).size(20.dp)
                )
                Text(text = screen.screenId, fontWeight =
                    if(state.screenViewer == screen.screenId) FontWeight.Bold
                    else FontWeight.Normal
                )
            }
        }
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
