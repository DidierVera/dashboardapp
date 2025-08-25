@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.components.background.LoadBackground
import com.came.parkare.dashboardapp.ui.components.elements.BuildBoxView
import com.came.parkare.dashboardapp.ui.components.elements.BuildColumnView
import com.came.parkare.dashboardapp.ui.components.elements.BuildRowView
import com.came.parkare.dashboardapp.ui.components.elements.BuildTextView
import com.came.parkare.dashboardapp.ui.screens.settings.components.BuildElement
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.components.DeviceListView
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.components.LoadElementImage
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.config_to_share_label
import dashboardapp.composeapp.generated.resources.content_label
import dashboardapp.composeapp.generated.resources.dashboard_backgroud
import dashboardapp.composeapp.generated.resources.device_list_label
import dashboardapp.composeapp.generated.resources.device_saved_message
import dashboardapp.composeapp.generated.resources.dialog_message_label
import dashboardapp.composeapp.generated.resources.ic_item_arrow
import dashboardapp.composeapp.generated.resources.share_button
import dashboardapp.composeapp.generated.resources.share_config_option
import dashboardapp.composeapp.generated.resources.share_configuration_message
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ShareConfigTab() {
    val viewModel: ShareConfigViewModel = koinViewModel()

    viewModel.initConfig()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)) {
        TabTitle(Res.string.share_config_option)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(
                modifier = Modifier.padding(8.dp).weight(0.25f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(Res.string.device_list_label),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                DeviceListView()
            }
            VerticalDivider()
            ConfigToBeShared(modifier = Modifier.weight(0.30f))
            ElementsList(modifier = Modifier.weight(0.45f))
        }
    }
}

@Composable
private fun ElementsList(modifier: Modifier = Modifier) {
    val viewModel: ShareConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if (state.elementsByScreen.isNotEmpty()){
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
            VerticalDivider()
            LazyColumn {
                item {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(4.dp)){
                        val background = state.imagesSource.firstOrNull { it.fileName?.contains("background") == true }
                        LoadBackground(background)
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(12.dp, 0.dp)) {
                            state.elementsByScreen.forEach { mItem ->
                                BuildElement(mItem, state.textSizeScale, state.imagesSource)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ConfigToBeShared(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        HeaderConfigToShare()
        Box(modifier = Modifier.shadowContainer()){
            LoadScreensAndElements()
        }
    }
}


@Composable
private fun LoadScreensAndElements() {
    val viewModel: ShareConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = Modifier.heightIn(max = 600.dp)) {
        items(state.configToShare){ screen ->
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
private fun HeaderConfigToShare() {
    val viewModel: ShareConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Text(text = stringResource(Res.string.config_to_share_label),
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = stringResource(Res.string.config_to_share_label),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
        Text(text = state.configType)
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.widthIn(min = 450.dp)) {
        Text(text = stringResource(Res.string.content_label),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))

        val shareMessage = stringResource(Res.string.share_configuration_message)

        AppButton(
            text = stringResource(Res.string.share_button),
            isEnabled = state.allowedToShare,
            onClick = {
                viewModel.launchPasswordRequest(message = shareMessage) {
                    viewModel.shareConfig()
                }
            }
        )
    }
}
