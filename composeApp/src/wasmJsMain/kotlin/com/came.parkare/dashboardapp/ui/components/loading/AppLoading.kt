@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.LightBlackColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun AppLoading() {
    val viewModel: AppLoadingViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if(state) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Box(
                modifier = Modifier.fillMaxSize().background(LightBlackColor.copy(alpha = 0.8f))
                    .clickable(enabled = false) {}
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = CameBlueColor)
                Spacer(Modifier.height(8.dp))
                Text("Loading...", color = WhiteColor)
            }

        }
    }
}