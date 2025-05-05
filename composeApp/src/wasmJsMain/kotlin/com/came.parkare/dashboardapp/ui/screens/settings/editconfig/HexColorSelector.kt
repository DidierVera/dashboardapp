package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.hexToColor


@Composable
fun HexColorSelector(
    initialColor: Color = Color.Black,
    onColorChanged: (Color) -> Unit
) {
    var hexColor by remember { mutableStateOf(initialColor.toHex()) }
    var color by remember { mutableStateOf(initialColor) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Color preview box
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // HEX input field
        OutlinedTextField(
            value = hexColor,
            onValueChange = { newValue ->
                hexColor = newValue.take(7) // Limit to 7 characters (# + 6 hex digits)
                if (hexColor.matches(Regex("^#[0-9A-Fa-f]{0,6}$"))) {
                    if (hexColor.length == 7) { // Complete HEX code
                        color = hexToColor(hexColor)
                        onColorChanged(color)
                    }
                }
            },
            label = { Text("HEX Color") },
            placeholder = { Text("#RRGGBB") },
            visualTransformation = HexColorTransformation(),
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sliders for RGB components
        val rgb = color.toRgb()
        ColorSlider(
            value = rgb.red,
            onValueChange = { newValue ->
                color = color.copy(red = newValue / 255f)
                hexColor = color.toHex()
                onColorChanged(color)
            },
            label = "Red",
            color = Color.Red
        )

        ColorSlider(
            value = rgb.green,
            onValueChange = { newValue ->
                color = color.copy(green = newValue / 255f)
                hexColor = color.toHex()
                onColorChanged(color)
            },
            label = "Green",
            color = Color.Green
        )

        ColorSlider(
            value = rgb.blue,
            onValueChange = { newValue ->
                color = color.copy(blue = newValue / 255f)
                hexColor = color.toHex()
                onColorChanged(color)
            },
            label = "Blue",
            color = Color.Blue
        )
    }
}

@Composable
fun ColorSlider(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
    color: Color
) {
    Column {
        Text(text = "$label: $value")
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
            valueRange = 0f..255f,
            colors = SliderDefaults.colors(
                thumbColor = color,
                activeTrackColor = color
            )
        )
    }
}
