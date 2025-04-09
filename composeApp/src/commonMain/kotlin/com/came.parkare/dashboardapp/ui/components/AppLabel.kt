package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppLabel(text: StringResource){
    Text(text = stringResource(text),
        color = Color.Gray,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(4.dp))
}