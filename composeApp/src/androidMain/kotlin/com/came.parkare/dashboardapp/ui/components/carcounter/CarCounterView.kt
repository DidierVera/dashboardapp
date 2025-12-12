@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.components.carcounter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun CarCounterView(modifier: Modifier = Modifier)  {
    val viewModel: CarCounterViewModel = koinViewModel()
    val counter by viewModel.counter.collectAsState()
    val show by viewModel.showCounter.collectAsState()

    if (show){
        Box(
            modifier = modifier
                .border(1.dp, WhiteColor, RoundedCornerShape(1.dp))
                .background(Color.Transparent)
                .padding(8.dp)
        ) {
            Text(
                text = "Count: \n$counter",
                modifier = Modifier.align(Alignment.Center),
                color = WhiteColor,
                style = LocalTextStyle.current.copy(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(0f, 0f),
                        blurRadius = 1f
                    )
                )
            )
        }
    }
}