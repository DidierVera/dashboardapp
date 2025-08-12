@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.templates

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.actions_label
import dashboardapp.composeapp.generated.resources.no_items_to_show_message
import dashboardapp.composeapp.generated.resources.screen_count_label
import dashboardapp.composeapp.generated.resources.template_name_label
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun TemplateScreen(onEditConfig: () -> Unit){
    val viewModel: TemplateViewModel = koinViewModel()
    viewModel.initScreen()
    LoadTemplates()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoadTemplates() {
    val viewModel: TemplateViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxWidth(0.7f).floatingButton(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        stickyHeader { TemplateHeader() }
        item { HorizontalDivider() }
        if (state.items.isEmpty()) {
            item { EmptyListMessage() }
        }
        items(state.items){ template ->
            Text(text = template.templateName)
        }
    }
}

@Composable
private fun TemplateHeader(){
    Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        HeaderItem(Res.string.template_name_label, modifier = Modifier.weight(1f))
        HeaderItem(Res.string.screen_count_label, modifier = Modifier.weight(1f))
        HeaderItem(Res.string.actions_label, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun HeaderItem(textId: StringResource, modifier: Modifier){
    Text(text = stringResource(textId),
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        modifier = modifier)
}

@Composable
private fun TemplateItem(){

}

@Composable
private fun EmptyListMessage() {
    Text(text = stringResource(Res.string.no_items_to_show_message))
}