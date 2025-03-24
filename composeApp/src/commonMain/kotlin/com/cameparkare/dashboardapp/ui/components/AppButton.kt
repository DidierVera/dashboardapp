package com.cameparkare.dashboardapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.cameparkare.dashboardapp.ui.theme.BlackColor
import com.cameparkare.dashboardapp.ui.theme.WhiteColor

@Composable
fun AppButton(text: String, onClick: () -> Unit,buttonColors: ButtonColors = ButtonDefaults.buttonColors(
containerColor = WhiteColor,
contentColor = BlackColor)
) {
    Button(onClick = onClick, colors = buttonColors,
        shape = RoundedCornerShape(10)) {
        Text(text)
    }
}