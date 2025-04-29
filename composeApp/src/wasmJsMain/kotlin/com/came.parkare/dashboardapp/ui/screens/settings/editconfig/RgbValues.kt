package com.came.parkare.dashboardapp.ui.screens.settings.editconfig

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

data class RgbValues(val red: Int, val green: Int, val blue: Int)
fun Color.toHex(): String {
    val rgb = ((red * 255).toInt() shl 16) or
            ((green * 255).toInt() shl 8) or
            (blue * 255).toInt()
    return "#${(0xFFFFFF and rgb).toString(16).padStart(6, '0')}"
}

fun Color.toRgb(): RgbValues {
    return RgbValues(
        red = (red * 255).toInt(),
        green = (green * 255).toInt(),
        blue = (blue * 255).toInt()
    )
}

// Visual transformation for HEX input
class HexColorTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(text.text.uppercase()),
            offsetMapping = OffsetMapping.Identity
        )
    }
}