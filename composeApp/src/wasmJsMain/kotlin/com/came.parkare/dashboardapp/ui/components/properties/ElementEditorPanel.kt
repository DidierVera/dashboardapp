package com.came.parkare.dashboardapp.ui.components.properties

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.add_element_tooltip
import dashboardapp.composeapp.generated.resources.invalid_json_message
import dashboardapp.composeapp.generated.resources.json_tab_label
import dashboardapp.composeapp.generated.resources.move_down_tooltip
import dashboardapp.composeapp.generated.resources.move_up_tooltip
import dashboardapp.composeapp.generated.resources.properties_tab_label
import dashboardapp.composeapp.generated.resources.remove_element_tooltip
import dashboardapp.composeapp.generated.resources.structure_label
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSerializationApi::class)
private val elementJson = Json {
    prettyPrint = true
    prettyPrintIndent = " "
    encodeDefaults = true
}

fun ElementDto.toPrettyJson(): String = elementJson.encodeToString(ElementDto.serializer(), this)

fun parseElement(jsonCode: String): Result<ElementDto> =
    runCatching { elementJson.decodeFromString(ElementDto.serializer(), jsonCode) }

/**
 * Editor for the element selected on the screen preview, shared by the "Edit current config" tab and the
 * config editor. [jsonCode] is the source of truth: the properties tab edits it through dynamic fields
 * for the selected node of the structure tree, and the JSON tab edits it as plain text.
 *
 * @param selectionKey identifies the selected root element, the tree selection resets when it changes.
 * @param onJsonChange called while the JSON is typed in the JSON tab.
 * @param onElementChange called with the new root element after a change made from the properties tab.
 */
@Composable
fun ElementEditorPanel(
    jsonCode: String,
    selectionKey: Any?,
    onJsonChange: (String) -> Unit,
    onElementChange: (ElementDto) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val parsed = remember(jsonCode) { parseElement(jsonCode) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 },
                text = { Text(stringResource(Res.string.properties_tab_label)) })
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 },
                text = { Text(stringResource(Res.string.json_tab_label)) })
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            // The scroll starts from the top each time the tab changes
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(key(selectedTab) { rememberScrollState() })
                .padding(4.dp)
        ) {
            val error = parsed.exceptionOrNull()
            if (error != null) {
                Text(
                    text = stringResource(Res.string.invalid_json_message, error.message.orEmpty().take(200)),
                    color = MaterialTheme.colorScheme.error
                )
            }
            val root = parsed.getOrNull()
            if (selectedTab == 0 && root != null) {
                PropertiesContent(root, selectionKey, onElementChange)
            } else if (selectedTab == 1) {
                TextField(
                    value = jsonCode,
                    onValueChange = onJsonChange,
                    modifier = Modifier.fillMaxWidth()
                )
                ApplyChangesButton(isEnabled = root != null) { root?.let(onElementChange) }
            }
        }
    }
}

@Composable
private fun PropertiesContent(root: ElementDto, selectionKey: Any?, onElementChange: (ElementDto) -> Unit) {
    var storedPath by remember(selectionKey) { mutableStateOf<ElementPath>(emptyList()) }
    // The path can become invalid when the element is replaced from the JSON tab
    val selectedPath = if (root.elementAt(storedPath) != null) storedPath else emptyList()
    val selected = root.elementAt(selectedPath) ?: root

    Text(stringResource(Res.string.structure_label),
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
    ElementStructureTree(
        root = root,
        selectedPath = selectedPath,
        onSelect = { storedPath = it },
        onMove = { path, offset ->
            val newRoot = root.moveAt(path, offset)
            if (newRoot != root) {
                storedPath = path.dropLast(1) + (path.last() + offset)
                onElementChange(newRoot)
            }
        },
        onRemove = { path ->
            storedPath = path.dropLast(1)
            onElementChange(root.removeAt(path))
        },
        onAdd = { parentPath, type ->
            val position = root.elementAt(parentPath)?.children()?.size ?: return@ElementStructureTree
            storedPath = parentPath + position
            onElementChange(root.addChildAt(parentPath, newElement(type)))
        }
    )

    key(selectionKey, selectedPath, selected) {
        ElementProperties(selected) { updated -> onElementChange(root.replaceAt(selectedPath, updated)) }
    }
}

/** Dynamic fields for the element type. */
@Composable
fun ElementProperties(element: ElementDto, onUpdate: (ElementDto) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        when (element) {
            is ElementDto.BoxDto -> BoxProperties(element.data, onUpdate)
            is ElementDto.ColumnDto -> ColumnProperties(element.data, onUpdate)
            is ElementDto.ImageDto -> ImageProperties(element.data, onUpdate)
            is ElementDto.RowDto -> RowProperties(element.data, onUpdate)
            is ElementDto.SpacerDto -> SpacerProperties(element.data, onUpdate)
            is ElementDto.TextDto -> TextProperties(element.data, onUpdate)
            is ElementDto.VideoDto -> VideoProperties(element.data, onUpdate)
        }
    }
}

@Composable
fun ElementStructureTree(
    root: ElementDto,
    selectedPath: ElementPath,
    onSelect: (ElementPath) -> Unit,
    onMove: (ElementPath, Int) -> Unit,
    onRemove: (ElementPath) -> Unit,
    onAdd: (ElementPath, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        TreeNode(root, emptyList(), siblings = 1, selectedPath, onSelect, onMove, onRemove, onAdd)
    }
}

@Composable
private fun TreeNode(
    element: ElementDto,
    path: ElementPath,
    siblings: Int,
    selectedPath: ElementPath,
    onSelect: (ElementPath) -> Unit,
    onMove: (ElementPath, Int) -> Unit,
    onRemove: (ElementPath) -> Unit,
    onAdd: (ElementPath, String) -> Unit
) {
    val isSelected = path == selectedPath
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface.copy(alpha = 0f),
                RoundedCornerShape(6.dp)
            )
            .clickable { onSelect(path) }
            .padding(start = (path.size * 16).dp + 4.dp, end = 4.dp)
    ) {
        Text(
            text = element.typeName(),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(end = 8.dp, top = 6.dp, bottom = 6.dp)
        )
        Text(
            text = element.summary(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        // Keeps the arrows and the bin aligned on the nodes that can't have children
        if (element.isContainer()) AddElementAction { type -> onAdd(path, type) }
        else Spacer(modifier = Modifier.size(28.dp))
        if (path.isNotEmpty()) {
            TreeAction(Icons.Default.KeyboardArrowUp, stringResource(Res.string.move_up_tooltip),
                enabled = path.last() > 0) { onMove(path, -1) }
            TreeAction(Icons.Default.KeyboardArrowDown, stringResource(Res.string.move_down_tooltip),
                enabled = path.last() < siblings - 1) { onMove(path, 1) }
            TreeAction(Icons.Default.Delete, stringResource(Res.string.remove_element_tooltip)) { onRemove(path) }
        }
    }
    val children = element.children()
    children.forEachIndexed { index, child ->
        TreeNode(child, path + index, children.size, selectedPath, onSelect, onMove, onRemove, onAdd)
    }
}

@Composable
private fun AddElementAction(onAdd: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        TreeAction(Icons.Default.Add, stringResource(Res.string.add_element_tooltip)) { expanded = true }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            newElementTypes.forEach { type ->
                DropdownMenuItem(
                    text = { Text(type) },
                    onClick = {
                        expanded = false
                        onAdd(type)
                    }
                )
            }
        }
    }
}

@Composable
private fun TreeAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    description: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, enabled = enabled, modifier = Modifier.size(28.dp)) {
        Icon(icon, contentDescription = description, modifier = Modifier.size(18.dp))
    }
}
