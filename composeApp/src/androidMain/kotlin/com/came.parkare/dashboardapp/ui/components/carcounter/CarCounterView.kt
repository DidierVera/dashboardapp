@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.components.carcounter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun CarCounterView(modifier: Modifier = Modifier)  {
    val viewModel: CarCounterViewModel = koinViewModel()
    val counter by viewModel.counter.collectAsState()
    val show by viewModel.showCounter.collectAsState()

    if (show){
        Box(modifier = modifier){
            Text(text = "Count: \n $counter", modifier = Modifier.align(Alignment.Center).padding(8.dp))
        }
    }
}