package com.came.parkare.dashboardapp.ui.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ScreenAlignment
import com.came.parkare.dashboardapp.ui.components.properties.PropertyNumberField
import com.came.parkare.dashboardapp.ui.components.properties.PropertySection
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.content_alignment_hint
import dashboardapp.composeapp.generated.resources.content_alignment_label
import dashboardapp.composeapp.generated.resources.margin_bottom_label
import dashboardapp.composeapp.generated.resources.margin_left_label
import dashboardapp.composeapp.generated.resources.margin_right_label
import dashboardapp.composeapp.generated.resources.margin_top_label
import dashboardapp.composeapp.generated.resources.screen_properties_label
import org.jetbrains.compose.resources.stringResource

data class ScreenMargins(val top: Int, val bottom: Int, val left: Int, val right: Int)

/**
 * Margins and position of the content of a screen. Every valid change is sent at once, so the
 * preview follows it; [selectionKey] resets the fields when another screen is selected.
 */
@Composable
fun ScreenPropertiesPanel(
    selectionKey: Any?,
    margins: ScreenMargins,
    alignment: ScreenAlignment,
    onMarginsChange: (ScreenMargins) -> Unit,
    onAlignmentChange: (ScreenAlignment) -> Unit,
    modifier: Modifier = Modifier
) {
    var top by remember(selectionKey) { mutableStateOf(margins.top.toString()) }
    var bottom by remember(selectionKey) { mutableStateOf(margins.bottom.toString()) }
    var left by remember(selectionKey) { mutableStateOf(margins.left.toString()) }
    var right by remember(selectionKey) { mutableStateOf(margins.right.toString()) }

    fun publish() {
        // Margins can not be negative (Compose padding); an invalid field keeps the last value
        val values = listOf(top, bottom, left, right).map { it.trim().toIntOrNull()?.takeIf { v -> v >= 0 } }
        if (values.any { it == null }) return
        onMarginsChange(ScreenMargins(values[0]!!, values[1]!!, values[2]!!, values[3]!!))
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        PropertySection(title = stringResource(Res.string.screen_properties_label)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.Top) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(Modifier.weight(1f)) {
                            PropertyNumberField(stringResource(Res.string.margin_top_label), top) { top = it; publish() }
                        }
                        Box(Modifier.weight(1f)) {
                            PropertyNumberField(stringResource(Res.string.margin_bottom_label), bottom) { bottom = it; publish() }
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(Modifier.weight(1f)) {
                            PropertyNumberField(stringResource(Res.string.margin_left_label), left) { left = it; publish() }
                        }
                        Box(Modifier.weight(1f)) {
                            PropertyNumberField(stringResource(Res.string.margin_right_label), right) { right = it; publish() }
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(stringResource(Res.string.content_alignment_label), style = MaterialTheme.typography.labelMedium)
                    AlignmentSelector(selected = alignment, onSelect = onAlignmentChange)
                }
            }
            Text(
                stringResource(Res.string.content_alignment_hint),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

/** 3 x 3 grid: each cell places a dot where the content goes. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlignmentSelector(selected: ScreenAlignment, onSelect: (ScreenAlignment) -> Unit) {
    val rows = ScreenAlignment.entries.chunked(3)
    Column(
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        rows.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                row.forEach { option ->
                    val isSelected = option == selected
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        tooltip = { PlainTooltip { Text(option.key) } },
                        state = rememberTooltipState()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    if (isSelected) CameBlueColor else Color(0xFFEDEDED),
                                    RoundedCornerShape(4.dp)
                                )
                                .clickable { onSelect(option) }
                                .padding(5.dp),
                            contentAlignment = option.toBoxAlignment()
                        ) {
                            Box(
                                Modifier
                                    .size(8.dp)
                                    .background(if (isSelected) Color.White else Color.Gray, CircleShape)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun ScreenAlignment.toBoxAlignment(): Alignment = when (this) {
    ScreenAlignment.TOP_START -> Alignment.TopStart
    ScreenAlignment.TOP_CENTER -> Alignment.TopCenter
    ScreenAlignment.TOP_END -> Alignment.TopEnd
    ScreenAlignment.CENTER_START -> Alignment.CenterStart
    ScreenAlignment.CENTER -> Alignment.Center
    ScreenAlignment.CENTER_END -> Alignment.CenterEnd
    ScreenAlignment.BOTTOM_START -> Alignment.BottomStart
    ScreenAlignment.BOTTOM_CENTER -> Alignment.BottomCenter
    ScreenAlignment.BOTTOM_END -> Alignment.BottomEnd
}
