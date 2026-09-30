package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.TextDataDto
import com.came.parkare.dashboardapp.ui.components.DropDownControl
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.add_translation_button
import dashboardapp.composeapp.generated.resources.content_section_label
import dashboardapp.composeapp.generated.resources.default_text_label
import dashboardapp.composeapp.generated.resources.font_weight_label
import dashboardapp.composeapp.generated.resources.item_id_label
import dashboardapp.composeapp.generated.resources.lang_label
import dashboardapp.composeapp.generated.resources.padding_label
import dashboardapp.composeapp.generated.resources.style_section_label
import dashboardapp.composeapp.generated.resources.text_color_label
import dashboardapp.composeapp.generated.resources.text_size_label
import dashboardapp.composeapp.generated.resources.translations_label
import dashboardapp.composeapp.generated.resources.value_label
import org.jetbrains.compose.resources.stringResource

private val fontWeights = listOf("Bold", "Medium", "Regular")

@Stable
private class TranslationDraft(lang: String, value: String) {
    var lang by mutableStateOf(lang)
    var value by mutableStateOf(value)
}

@Composable
fun TextProperties(
    text: TextDataDto,
    onUpdate: (ElementDto) -> Unit
) {
    var itemId by remember(text) { mutableStateOf(text.dashboardItemId) }
    var defaultText by remember(text) { mutableStateOf(text.defaultText) }
    var textColor by remember(text) { mutableStateOf(text.textColor) }
    var textSize by remember(text) { mutableStateOf(text.textSize.toString()) }
    var padding by remember(text) { mutableStateOf(text.padding.asField()) }
    var fontWeight by remember(text) { mutableStateOf(text.fontWeight) }
    val translations = remember(text) {
        mutableStateListOf<TranslationDraft>().apply {
            text.translations.orEmpty().forEach { (lang, value) -> add(TranslationDraft(lang, value)) }
        }
    }
    val binding = remember(text) { BindingDraft(text.dataKey, text.ditTypeCode, text.validValue) }

    PropertySection(stringResource(Res.string.content_section_label)) {
        PropertyTextField(stringResource(Res.string.item_id_label), itemId) { itemId = it }
        PropertyTextField(stringResource(Res.string.default_text_label), defaultText, singleLine = false) {
            defaultText = it
        }
    }

    PropertySection(stringResource(Res.string.style_section_label)) {
        PropertyColorField(stringResource(Res.string.text_color_label), textColor) { textColor = it }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.text_size_label), textSize) { textSize = it }
            }
            Row(modifier = Modifier.weight(1f)) {
                PropertyNumberField(stringResource(Res.string.padding_label), padding) { padding = it }
            }
        }
        val weightOptions = if (fontWeight in fontWeights) fontWeights else fontWeights + fontWeight
        DropDownControl(weightOptions, fontWeight, label = stringResource(Res.string.font_weight_label)) {
            fontWeight = it
        }
    }

    PropertySection(stringResource(Res.string.translations_label)) {
        translations.forEach { translation ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = translation.lang,
                    onValueChange = { translation.lang = it },
                    label = { Text(stringResource(Res.string.lang_label)) },
                    singleLine = true,
                    modifier = Modifier.weight(0.3f)
                )
                OutlinedTextField(
                    value = translation.value,
                    onValueChange = { translation.value = it },
                    label = { Text(stringResource(Res.string.value_label)) },
                    modifier = Modifier.weight(0.7f)
                )
                IconButton(onClick = { translations.remove(translation) }) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                }
            }
        }
        TextButton(onClick = { translations.add(TranslationDraft("lang${translations.size + 1}", defaultText)) }) {
            Text(stringResource(Res.string.add_translation_button))
        }
    }

    BindingFields(binding)

    ApplyChangesButton {
        val newTranslations = translations
            .filter { it.lang.isNotBlank() }
            .associate { it.lang.trim() to it.value }
        onUpdate(
            ElementDto.TextDto(
                elementType = "text",
                data = text.copy(
                    dashboardItemId = itemId.trim(),
                    defaultText = defaultText,
                    textColor = textColor,
                    textSize = textSize.toIntOrDefault(text.textSize),
                    padding = padding.trim().toIntOrNull(),
                    fontWeight = fontWeight,
                    translations = newTranslations.ifEmpty { null },
                    dataKey = binding.dataKeyValue,
                    ditTypeCode = binding.ditTypeCodeValue,
                    validValue = binding.validValueValue
                )
            )
        )
    }
}
