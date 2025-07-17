package com.came.parkare.dashboardapp.ui.theme.style

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.floatingButton(
    isSelected: Boolean = false,
    background: Color = MaterialTheme.colorScheme.surface, onSelectClick: (() -> Unit)? = null):Modifier {
    return this.padding(4.dp) // Add padding around each row
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        )
        .run {
            if (onSelectClick != null) {
                clickable { onSelectClick() }
            } else {
                this
            }
        }
        .background(
            color = if(isSelected) MaterialTheme.colorScheme.onSecondary
            else background,
            shape = RoundedCornerShape(8.dp)
        )
        .padding(4.dp)
}

@Composable
fun Modifier.shadowContainer() = this
    .padding(4.dp) // Add padding around each row
    .shadow(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ).background(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp)
    )
    .padding(4.dp)