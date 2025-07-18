@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.screens.settings.components.BuildElement
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.ShareConfigViewModel
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.apply_button
import dashboardapp.composeapp.generated.resources.dashboard_backgroud
import dashboardapp.composeapp.generated.resources.edit_current_config_option
import dashboardapp.composeapp.generated.resources.element_code_label
import dashboardapp.composeapp.generated.resources.file_content_label
import dashboardapp.composeapp.generated.resources.ic_item_arrow
import dashboardapp.composeapp.generated.resources.no_items_to_show_message
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.screen_list_label
import dashboardapp.composeapp.generated.resources.screen_preview_label
import dashboardapp.composeapp.generated.resources.screens_label
import dashboardapp.composeapp.generated.resources.select_screen_message
import dashboardapp.composeapp.generated.resources.send_button
import dashboardapp.composeapp.generated.resources.update_button
import dashboardapp.composeapp.generated.resources.update_config_message
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun EditConfigTab(modifier: Modifier = Modifier){
    val viewModel: EditConfigViewModel = koinViewModel()

    viewModel.initConfig()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(8.dp)) {
        TabTitle(Res.string.edit_current_config_option)

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ListOfScreens(modifier = Modifier.weight(0.25f))

            VerticalDivider()

            ElementsList(modifier = Modifier.weight(0.45f))

            VerticalDivider()
            Column(modifier = Modifier.weight(0.35f)) {
                EditorTitle()
                EditorField()
            }
        }
    }
}

@Composable
private fun ElementsList(modifier: Modifier = Modifier) {
    val viewModel: EditConfigViewModel = koinViewModel()
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
                                    BuildElement(mItem, state.textSizeScale, state.imagesSource)
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

@Composable
private fun LoadBackground(background: ImagesFileModel?) {
    if(background != null){
        Base64Image(background.fileContent.orEmpty(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth)
    }else {
        Image(
            painter = painterResource(Res.drawable.dashboard_backgroud),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(4.dp).fillMaxSize()
                .background(Color.LightGray)
        )
    }
}


@Composable
private fun EditorField() {
    val viewModel: EditConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn {
        item {
            Box(modifier = Modifier.fillMaxWidth()
                .padding(4.dp) // Add padding around each row
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp)){
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = state.contentFile, onValueChange = {
                        viewModel.setValues(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun EditorTitle(){
    val viewModel: EditConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()) {
        val updateMessage = stringResource(Res.string.update_config_message)
        AppButton(
            text = stringResource(Res.string.update_button), onClick = {
                viewModel.updateConfig(updateMessage)
            },
            isEnabled = (!state.screenViewer.isNullOrBlank() && state.contentFile.isNotEmpty())
        )
        HorizontalDivider()
        Text(
            text = stringResource(Res.string.element_code_label),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
private fun ListOfScreens(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        Text(text = stringResource(Res.string.screen_list_label),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Box(modifier = Modifier.shadowContainer()){
            LoadScreensAndElements()
        }
    }
}

@Composable
private fun LoadScreensAndElements() {
    val viewModel: EditConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = Modifier.heightIn(max = 600.dp)) {
        items(state.screens){ screen ->
            Row(modifier = Modifier
                .widthIn(min = 450.dp)
                .floatingButton(
                    isSelected = screen.screenId == state.screenViewer,
                    onSelectClick = {
                        viewModel.selectScreen(screen.toModel()) })
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