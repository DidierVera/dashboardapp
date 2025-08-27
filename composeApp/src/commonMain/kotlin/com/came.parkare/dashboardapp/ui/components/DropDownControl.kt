package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DropDownControl(options: List<String>, selectedItem: String, label: String? = null, onSelect: (String) -> Unit) {
    CustomDropdownSelector(
        items = options,
        selectedItem = selectedItem,
        onItemSelected = { onSelect.invoke(it) },
        label = label,
        itemContent = { item ->
            Text(
                text = item,
                fontWeight = if (selectedItem == item) FontWeight.SemiBold else FontWeight.Normal,
                modifier = Modifier.padding(4.dp))
        },
        selectedItemContent = { device ->
            Text(device, fontWeight = FontWeight.Bold)
        }
    )
}