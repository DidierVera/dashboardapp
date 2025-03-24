package com.cameparkare.dashboardapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.theme.BlackColor
import com.cameparkare.dashboardapp.ui.theme.WhiteColor

@Composable
fun AppButton(text: String, onClick: () -> Unit,buttonColors: ButtonColors = ButtonDefaults.buttonColors(
containerColor = WhiteColor,
contentColor = BlackColor), modifier: Modifier = Modifier.padding(4.dp, 0.dp)
) {
    Button(onClick = onClick, colors = buttonColors,
        shape = RoundedCornerShape(10), modifier = modifier) {
        Text(text)
    }
}