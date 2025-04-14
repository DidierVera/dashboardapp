package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    buttonColors:
    ButtonColors = ButtonDefaults.buttonColors(
        containerColor = WhiteColor,
        contentColor = BlackColor),
    modifier: Modifier = Modifier.padding(4.dp, 0.dp)
) {
    Button(onClick = onClick, colors = buttonColors,
        shape = RoundedCornerShape(10), modifier = modifier.clickable {

        }) {
        Text(text)
    }
}