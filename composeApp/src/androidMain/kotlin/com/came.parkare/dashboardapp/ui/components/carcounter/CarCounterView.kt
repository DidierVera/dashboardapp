@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.components.carcounter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Box(
            modifier = modifier
                .border(1.dp, Color.White, RoundedCornerShape(4.dp))
                .background(Color.Transparent)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ShadowText("Initial Date: ${viewModel.formatDate(initialDate)}")
                ShadowText("Final Date: ${viewModel.formatDate(finalDate)}")
                ShadowText("Timer Interval: $timerInterval minutes")
                ShadowText("Entered cars: $counter")

                Text(
                    text = "Next update in: ${calculateTimeRemaining(finalDate)}",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun ShadowText(text: String) {
    Text(
        text = text,
        color = Color.White,
        style = TextStyle(
            fontSize = 20.sp,
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(2f, 2f),
                blurRadius = 4f
            )
        )
    )
}

private fun calculateTimeRemaining(final: Date): String {
    val now = Date()
    val remaining = final.time - now.time

    return if (remaining > 0) {
        val minutes = (remaining / (1000 * 60)).toInt()
        "$minutes min"
    } else {
        "Ready to reset"
    }
}