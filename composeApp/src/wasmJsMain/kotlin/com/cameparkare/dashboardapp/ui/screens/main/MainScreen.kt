package com.cameparkare.dashboardapp.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.cameparkare.dashboardapp.ui.resources.AppStrings

@Composable
fun MainScreen(){
    Title()
}

@Composable
fun Title() {
    LazyColumn {
        item { Text(text = AppStrings.configTitle) }
    }
}
