package com.came.parkare.dashboardapp.ui.screens.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabTitle(textRes: StringResource, modifier: Modifier = Modifier.padding(4.dp)) {
    Text(
        text = stringResource(textRes),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium.copy(textDecoration = TextDecoration.Underline),
        modifier = modifier
    )
}