package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.theme.hexToColor
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.apply_changes_button
import org.jetbrains.compose.resources.stringResource

@Composable
fun PropertySection(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider()
        Text(title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
        content()
    }
}

@Composable
fun PropertyTextField(
    label: String,
    value: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        modifier = Modifier.fillMaxWidth()
    )
}

/** Integer field, an empty value means "not set" for the nullable properties. */
@Composable
fun PropertyNumberField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.isEmpty() || newValue.matches(Regex("^-?\\d{0,6}$"))) onValueChange(newValue)
        },
        label = { Text(label) },
        singleLine = true,
        isError = value.isNotEmpty() && value.toIntOrNull() == null,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PropertyColorField(label: String, value: String, onValueChange: (String) -> Unit) {
    val isValid = value.matches(Regex("^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$"))
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.trim().take(9)) },
        label = { Text(label) },
        placeholder = { Text("#RRGGBB") },
        singleLine = true,
        isError = value.isNotEmpty() && !isValid,
        leadingIcon = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    .background(if (isValid) hexToColor(value) else Color.Transparent, RoundedCornerShape(4.dp))
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PropertyCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 8.dp)) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(label)
    }
}

@Composable
fun ApplyChangesButton(isEnabled: Boolean = true, onClick: () -> Unit) {
    AppButton(
        text = stringResource(Res.string.apply_changes_button),
        onClick = onClick,
        isEnabled = isEnabled,
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
    )
}

fun String.toIntOrDefault(default: Int): Int = trim().toIntOrNull() ?: default
fun Int?.asField(): String = this?.toString().orEmpty()
fun String.blankToNull(): String? = trim().ifEmpty { null }
