package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor

/**
 * Enhanced Dropdown with custom item rendering.
 * @param items List of items of type T
 * @param selectedItem Currently selected item
 * @param onItemSelected Callback when an item is selected
 * @param itemContent Custom composable for each dropdown item
 * @param selectedItemContent Custom composable for the selected item display
 */
@Composable
fun <T> CustomDropdownSelector(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = WhiteColor,
        contentColor = BlackColor),
    label: String? = null,
    itemContent: @Composable (T) -> Unit = { Text(it.toString()) },
    selectedItemContent: @Composable (T) -> Unit = { Text(it.toString()) },
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(4.dp, 0.dp)) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        OutlinedButton(
            border = BorderStroke(0.2.dp, Color.LightGray),
            shape = RoundedCornerShape(10),
            onClick = {expanded = !expanded},
            enabled = true,
            colors = buttonColors) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.widthIn(min = 130.dp)) {
                Box(modifier = Modifier) {
                    selectedItemContent(selectedItem)
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Arrow"
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { itemContent(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}