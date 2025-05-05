@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.toModel
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.BuildComposable
import com.came.parkare.dashboardapp.ui.screens.settings.components.TabTitle
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.dashboard_backgroud
import dashboardapp.composeapp.generated.resources.edit_current_config_option
import dashboardapp.composeapp.generated.resources.file_content_label
import dashboardapp.composeapp.generated.resources.save_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun EditConfigTab(modifier: Modifier = Modifier){
    val viewModel: EditConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    viewModel.getCurrentScreenConfig()

    Column(modifier = modifier.padding(8.dp)) {
        TabTitle(Res.string.edit_current_config_option)

        Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            LazyColumn(modifier = Modifier.padding(16.dp).weight(0.35f)) {
                items(items = state.screens){ screen ->
                    LoadScreen(screen)
                }
            }

            LazyColumn(modifier = Modifier.weight(0.65f)) {
                item { EditorTitle() }
                item { EditorField(viewModel, state) }
            }
        }
    }
}

@Composable
private fun LoadScreen(screen: ScreenDto) {
    val viewModel: EditConfigViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.background(
            if(screen == state.containerScreen) CameBlueColor.copy(alpha = 0.2f) else Color.White
        )
    ) {
        HorizontalDivider()
        Text(
            text = screen.screenId,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold))
        Box(Modifier.fillMaxSize().padding(4.dp)) {
            LoadBackground()
            LoadDashboardItems(modifier = Modifier.align(Alignment.Center),
                screen =  screen.toModel())
        }
    }
}

@Composable
private fun LoadDashboardItems(modifier: Modifier = Modifier, screen: ScreenModel){
    val viewModel: EditConfigViewModel = koinViewModel()
    val items: List<ElementModel> = screen.elements
    if (items.isEmpty()) return
    val boxMargin = PaddingValues(screen.marginLeft.dp, screen.marginTop.dp, screen.marginRight.dp, screen.marginBottom.dp)

    Column(modifier = modifier.padding(boxMargin),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ){
        for(i in items.indices){
            val mItem = items[i]
            val textSizeScale = 10
            Box(modifier = Modifier.clickable {
                viewModel.selectItemOnScreen(mItem, i, screen)
            }) {
                BuildComposable(elementModel = mItem, textSizeScale = textSizeScale)
            }
        }
    }
}
@Composable
private  fun LoadBackground(){
    Image(
        painter = painterResource(Res.drawable.dashboard_backgroud),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .padding(4.dp).fillMaxSize()
            .background(Color.LightGray)
    )
}


@Composable
private fun EditorField(viewModel: EditConfigViewModel, state: EditConfigState) {
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

@Composable
private fun EditorTitle(){
    val viewModel: EditConfigViewModel = koinViewModel()
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(Res.string.file_content_label),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium)
        AppButton(text = "APPLY",
            buttonColors = ButtonDefaults.buttonColors().copy(
                containerColor = Color.LightGray,
                contentColor = BlackColor),
            onClick = {
            viewModel.applyChanges()
        })
        AppButton(text = stringResource(Res.string.save_button), onClick = {
            viewModel.saveChanges()
        })
    }
}