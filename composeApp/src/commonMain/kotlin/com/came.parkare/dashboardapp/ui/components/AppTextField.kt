package com.came.parkare.dashboardapp.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AppTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) }
    )
}