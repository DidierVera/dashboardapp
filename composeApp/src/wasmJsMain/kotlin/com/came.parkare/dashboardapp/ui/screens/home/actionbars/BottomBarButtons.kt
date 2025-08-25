@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.actionbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.screens.home.HomeViewModel
import com.came.parkare.dashboardapp.ui.screens.home.components.TooltipButton
import com.came.parkare.dashboardapp.ui.screens.home.components.statusbar.StatusBarView
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.blank_elements_label
import dashboardapp.composeapp.generated.resources.default_elements_label
import dashboardapp.composeapp.generated.resources.default_screens_label
import dashboardapp.composeapp.generated.resources.properties_label
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun BottomBarButtons() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()


    Column {
        StatusBarView(modifier = Modifier.align(Alignment.Start))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(4.dp, 0.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                TooltipButton(onClick = {viewModel.displayBlankElement()},
                    tooltipTextId = Res.string.blank_elements_label){
                    Icon(if(state.displayBlankElements) Icons.Default.Close else Icons.Default.Create,
                        contentDescription = null)
                }
                TooltipButton(onClick = {viewModel.displayElements()},
                    tooltipTextId = Res.string.default_elements_label){
                    Icon(if(state.displayDefaultElements) Icons.Default.Close else Icons.Default.Favorite,
                        contentDescription = null)
                }
                TooltipButton(onClick = {viewModel.displayDefaultScreens()},
                    tooltipTextId = Res.string.default_screens_label){
                    Icon(if(state.displayDefaultScreens) Icons.Default.Close else Icons.Default.Home,
                        contentDescription = null)
                }
            }
            TooltipButton(onClick = {viewModel.displayProperties()},
                tooltipTextId = Res.string.properties_label){
                Icon(if(state.displayProperties) Icons.Default.Close else Icons.Default.Build,
                    contentDescription = null)
            }
        }
    }
}
