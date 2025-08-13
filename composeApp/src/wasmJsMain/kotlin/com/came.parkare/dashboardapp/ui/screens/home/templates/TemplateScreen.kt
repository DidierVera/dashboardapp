@file:OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class)

package com.came.parkare.dashboardapp.ui.screens.home.templates

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.actions_label
import dashboardapp.composeapp.generated.resources.default_template_label
import dashboardapp.composeapp.generated.resources.edit_existing_config_label
import dashboardapp.composeapp.generated.resources.no_items_to_show_message
import dashboardapp.composeapp.generated.resources.saved_templates_label
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
    LoadTemplates(onEditConfig)
}

@Composable
fun LoadTemplates(onEditConfig: () -> Unit) {
    val viewModel: TemplateViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxWidth(0.7f).floatingButton(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        stickyHeader { TemplateHeader() }

        item { HorizontalDivider() }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { Text(text = stringResource(Res.string.default_template_label),
            fontSize = 10.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth().
            background(Color.LightGray.copy(alpha = 0.5f)).padding(4.dp)) }

        if (state.defaultTemplates.isEmpty()) {
            item { EmptyListMessage() }
        }

        items(state.defaultTemplates){ template ->
            TemplateItem(modifier = Modifier.fillMaxWidth(), template, onEditConfig)
        }

        item { HorizontalDivider() }
        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { Text(text = stringResource(Res.string.saved_templates_label),
            fontSize = 10.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth().
            background(Color.LightGray.copy(alpha = 0.5f)).padding(4.dp)) }
        if (state.existingItems.isEmpty()) {
            item { EmptyListMessage() }
        }
        items(state.existingItems){ template ->
            TemplateItem(modifier = Modifier.fillMaxWidth(), template, onEditConfig)
        }
    }
}

@Composable
private fun TemplateHeader(){
    Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        HeaderItem(Res.string.template_name_label, modifier = Modifier.weight(1f))
        HeaderItem(Res.string.screen_count_label, modifier = Modifier.weight(1f))
        HeaderItem(Res.string.actions_label, modifier = Modifier.weight(0.2f))
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
private fun TemplateItem(modifier: Modifier, template: ConfigTemplateModel,
                         onEditConfig: () -> Unit){
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = template.templateName, modifier = Modifier.weight(1f))
        Text(text = "${ template.screens.size }", modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
            modifier = Modifier.weight(0.2f)) {
            IconButton(onClick = {}){
                Icon(Icons.Default.Delete, contentDescription = null)
            }
            IconButton(onClick = {
                onEditConfig.invoke()
            }){
                Icon(Icons.Default.Edit, contentDescription = null)
            }

        }
    }

}

@Composable
private fun EmptyListMessage() {
    Text(text = stringResource(Res.string.no_items_to_show_message))
}