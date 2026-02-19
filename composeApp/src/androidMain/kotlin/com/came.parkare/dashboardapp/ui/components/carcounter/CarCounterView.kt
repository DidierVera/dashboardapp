@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.components.carcounter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.ui.theme.ArialNarrowFont
import com.came.parkare.dashboardapp.ui.theme.ArialRoundedFont
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.Rubik
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import java.util.Date
@Composable
fun CarCounterView(modifier: Modifier = Modifier)  {
    val viewModel: CarCounterViewModel = koinViewModel()
    val counter by viewModel.counter.collectAsState()
    val show by viewModel.showCounter.collectAsState()
    val timerInterval by viewModel.timer.collectAsState()

    val initialDate by viewModel.initialDate.collectAsState()
    val finalDate by viewModel.finalDate.collectAsState()

    if (show) {
        Box(modifier = modifier){
            Box(
                modifier = Modifier
                    .border(0.4.dp, Color.LightGray, RoundedCornerShape(2.dp))
                    .background(CameBlueColor.copy(alpha = 0.4f))
                    .padding(48.dp, 24.dp)
                    .align(Alignment.Center)
            ) {
                ShadowText("Entered cars: $counter/min")
            }
        }
    }
}

@Composable
private fun ShadowText(text: String) {
    Text(
        text = text,
        color = BlackColor,
        style = MaterialTheme.typography.labelLarge.copy(
            fontSize = 24.sp,
            fontFamily = Rubik
        )
    )
}