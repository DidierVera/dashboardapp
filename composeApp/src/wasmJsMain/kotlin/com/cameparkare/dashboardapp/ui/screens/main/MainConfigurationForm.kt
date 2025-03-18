package com.cameparkare.dashboardapp.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.controls.FormField

@Composable
fun MainConfigurationForm(){

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(1){

               // Terminal Connection Title
        Text(text = "Terminal Connection", style = MaterialTheme.typography.displaySmall)

        // IP Field
        FormField(label = "Ip", placeholder = "example http://192.168.20.36")

        // Port Field
        FormField(label = "Port", placeholder = "9011", keyboardType = KeyboardType.Number)

        // API Field
        FormField(label = "Api", placeholder = "signalr")

        // Main Layout Properties Title
        Text(text = "Main Layout Properties", style = MaterialTheme.typography.displaySmall)

        // Text Size Scale Field
        FormField(label = "Text size Scale", placeholder = "10", keyboardType = KeyboardType.Number)

        // Video Frame Switch
        var isVideoFrameEnabled by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Video frame")
            Switch(
                checked = isVideoFrameEnabled,
                onCheckedChange = { isVideoFrameEnabled = it }
            )
        }

        // Delay Time Field
        FormField(label = "Delay time", placeholder = "3", keyboardType = KeyboardType.Number)

        // Margins Fields
        Text(text = "Margins", style = MaterialTheme.typography.displayMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FormField(label = "Top", placeholder = "0", keyboardType = KeyboardType.Number, modifier = Modifier.weight(1f))
            FormField(label = "Bottom", placeholder = "0", keyboardType = KeyboardType.Number, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FormField(label = "Left", placeholder = "0", keyboardType = KeyboardType.Number, modifier = Modifier.weight(1f))
            FormField(label = "Right", placeholder = "0", keyboardType = KeyboardType.Number, modifier = Modifier.weight(1f))
        }

        // Add Screen Button
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Screen")
        }
        }
    }
}