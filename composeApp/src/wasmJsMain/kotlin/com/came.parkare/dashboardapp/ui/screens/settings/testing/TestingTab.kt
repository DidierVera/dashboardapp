@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.ic_item_arrow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun TestingTab() {
    val viewModel: TestingViewModel = koinViewModel()

    viewModel.initConfig()
    Row(modifier = Modifier.fillMaxSize()) {
        LoadScreensAndElements()
        VerticalDivider()

    }
}

@Composable
private fun LoadScreensAndElements() {
    val viewModel: TestingViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = Modifier.heightIn(max = 600.dp)) {
        items(state.screens){ screen ->
            Row(modifier = Modifier
                .widthIn(min = 450.dp)
                .floatingButton()
                .padding(8.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    painter = painterResource(Res.drawable.ic_item_arrow),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).size(20.dp)
                )
                Text(text = screen.screenId, fontWeight = FontWeight.Normal)
            }
        }
    }
}