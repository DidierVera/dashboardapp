package com.cameparkare.dashboardapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.components.states.TabContainerState

@Composable
fun TabsContainer(modifier: Modifier = Modifier, items: List<TabContainerState>) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = items.map { it.title }

    Column(modifier = modifier.fillMaxHeight().background(Color.LightGray)) {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.DarkGray,
            contentColor = MaterialTheme.colors.primary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) },
                    unselectedContentColor = Color.LightGray,
                    selectedContentColor = Color.White
                )
            }
        }

        // Content of the selected tab
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            items[selectedTabIndex].content.invoke()
        }
    }
}
