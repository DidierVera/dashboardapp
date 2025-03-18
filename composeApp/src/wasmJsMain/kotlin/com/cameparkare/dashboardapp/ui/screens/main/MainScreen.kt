package com.cameparkare.dashboardapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cameparkare.dashboardapp.ui.components.TabsContainer
import com.cameparkare.dashboardapp.ui.components.states.TabContainerState
import com.cameparkare.dashboardapp.ui.resources.AppStrings

@Composable
fun MainScreen(){
    Column(modifier = Modifier.fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally) {
        Title(modifier = Modifier.padding(14.dp))
        BodyContent()
    }
}

@Composable
fun BodyContent() {
    val tabItems = listOf( TabContainerState(
        title = AppStrings.initConfiguration,
        content = { MainConfiguration(modifier = Modifier.fillMaxSize()) }
    ),TabContainerState(
        title = AppStrings.properties,
        content = { Properties(modifier = Modifier.fillMaxSize()) }
    ))

    MaterialTheme {
        Row(modifier = Modifier.fillMaxSize()) {
            // Left panel with a tab bar
            TabsContainer(modifier = Modifier.weight(0.35f), tabItems)

            // Divider for a visual split
            Divider(color = Color.Gray, modifier = Modifier.width(1.dp))

            // Right panel as an empty container
            RightPanel(modifier = Modifier.weight(0.65f))
        }
    }
}


@Composable
fun RightPanel(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        ScreenBoardEditor()
    }
}


@Composable
private fun ScreenBoardEditor(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Screen board editor", color = Color.Black)
    }
}

@Composable
private fun Properties(modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Text("Properties tab")
    }
}

@Composable
private fun MainConfiguration(modifier: Modifier = Modifier){
    Box(modifier = modifier){
        MainConfigurationForm()
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        text = AppStrings.configTitle,
        fontSize = 24.sp,
        textAlign = TextAlign.Center, modifier = modifier)
}
