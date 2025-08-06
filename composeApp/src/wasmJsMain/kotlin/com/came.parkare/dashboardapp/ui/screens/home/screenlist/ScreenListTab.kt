@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.screenlist

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.ui.screens.home.elementlist.ElementListViewModel
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.default_screens_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ScreenListTab(modifier: Modifier = Modifier){
    val viewModel: ScreenListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    if (state.showTab){
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier.widthIn(max = 260.dp).verticalScroll(ScrollState(0))
                .fillMaxSize().floatingButton().padding(8.dp).graphicsLayer {
                    this.scaleX = 1.0f
                    this.scaleY = 1.0f
                }) {
            Text(stringResource(Res.string.default_screens_label), fontWeight = FontWeight.Bold)
            HorizontalDivider()
            LoadScreens(state.defaultScreens)
        }
    }
}

@Composable
private fun LoadScreens(defaultScreens: List<ScreenDto>) {

}
