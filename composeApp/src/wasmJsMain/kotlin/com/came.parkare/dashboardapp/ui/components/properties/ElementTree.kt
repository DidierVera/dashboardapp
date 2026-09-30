package com.came.parkare.dashboardapp.ui.components.properties

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.BoxDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ColumnDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ImageDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.RowDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.SpacerDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.TextDataDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.VideoDataDto

/**
 * Helpers to navigate and edit an element and its nested content.
 * A path is the list of child indexes from the root element, an empty path is the root itself.
 */
typealias ElementPath = List<Int>

fun ElementDto.children(): List<ElementDto> = when (this) {
    is ElementDto.BoxDto -> data.content
    is ElementDto.ColumnDto -> data.content
    is ElementDto.RowDto -> data.content
    else -> emptyList()
}

fun ElementDto.isContainer(): Boolean =
    this is ElementDto.BoxDto || this is ElementDto.ColumnDto || this is ElementDto.RowDto

fun ElementDto.withChildren(content: List<ElementDto>): ElementDto = when (this) {
    is ElementDto.BoxDto -> copy(data = data.copy(content = content))
    is ElementDto.ColumnDto -> copy(data = data.copy(content = content))
    is ElementDto.RowDto -> copy(data = data.copy(content = content))
    else -> this
}

fun ElementDto.elementAt(path: ElementPath): ElementDto? {
    var current: ElementDto = this
    for (index in path) {
        current = current.children().getOrNull(index) ?: return null
    }
    return current
}

fun ElementDto.replaceAt(path: ElementPath, element: ElementDto): ElementDto {
    if (path.isEmpty()) return element
    val children = children().toMutableList()
    val index = path.first()
    if (index !in children.indices) return this
    children[index] = children[index].replaceAt(path.drop(1), element)
    return withChildren(children)
}

fun ElementDto.removeAt(path: ElementPath): ElementDto {
    if (path.isEmpty()) return this
    val children = children().toMutableList()
    val index = path.first()
    if (index !in children.indices) return this
    if (path.size == 1) children.removeAt(index)
    else children[index] = children[index].removeAt(path.drop(1))
    return withChildren(children)
}

/** Moves the element at [path] one position up (-1) or down (+1) inside its parent. */
fun ElementDto.moveAt(path: ElementPath, offset: Int): ElementDto {
    if (path.isEmpty()) return this
    val parentPath = path.dropLast(1)
    val parent = elementAt(parentPath) ?: return this
    val children = parent.children().toMutableList()
    val from = path.last()
    val to = from + offset
    if (from !in children.indices || to !in children.indices) return this
    children.add(to, children.removeAt(from))
    return replaceAt(parentPath, parent.withChildren(children))
}

/** Adds [element] as the last child of the container at [parentPath]. */
fun ElementDto.addChildAt(parentPath: ElementPath, element: ElementDto): ElementDto {
    val parent = elementAt(parentPath) ?: return this
    if (!parent.isContainer()) return this
    return replaceAt(parentPath, parent.withChildren(parent.children() + element))
}

val newElementTypes = listOf("box", "column", "row", "text", "image", "spacer", "video")

/** New element with visible default values, so it can be seen on the preview as soon as it is added. */
fun newElement(type: String): ElementDto = when (type) {
    "box" -> ElementDto.BoxDto("box", BoxDataDto(
        backgroundColor = "#FFFFFF", density = 0, roundBorder = 0, hasShadow = false, padding = 0, content = emptyList()))
    "column" -> ElementDto.ColumnDto("column", ColumnDataDto(
        backgroundColor = "#FFFFFF", density = 0, roundBorder = 0, hasShadow = false, spacing = 0, padding = 0, content = emptyList()))
    "row" -> ElementDto.RowDto("row", RowDataDto(
        backgroundColor = "#FFFFFF", density = 0, roundBorder = 0, hasShadow = false, spacing = 10, padding = 0, content = emptyList()))
    "text" -> ElementDto.TextDto("text", TextDataDto(
        dashboardItemId = "new-text", defaultText = "New text", textSize = 30, textColor = "#FFFFFF",
        padding = 0, fontWeight = "Medium"))
    "image" -> ElementDto.ImageDto("image", ImageDataDto(dashboardItemId = "new-image"))
    "video" -> ElementDto.VideoDto("video", VideoDataDto(dashboardItemId = "new-video"))
    else -> ElementDto.SpacerDto("spacer", SpacerDataDto(value = 20))
}

fun ElementDto.typeName(): String = when (this) {
    is ElementDto.BoxDto -> "box"
    is ElementDto.ColumnDto -> "column"
    is ElementDto.ImageDto -> "image"
    is ElementDto.RowDto -> "row"
    is ElementDto.SpacerDto -> "spacer"
    is ElementDto.TextDto -> "text"
    is ElementDto.VideoDto -> "video"
}

/** Short description used to recognize the element in the structure tree. */
fun ElementDto.summary(): String = when (this) {
    is ElementDto.TextDto -> "\"${data.defaultText.replace("\r", "").replace("\n", " ")}\"" +
            if (data.dashboardItemId.isNotBlank()) "  #${data.dashboardItemId}" else ""
    is ElementDto.ImageDto -> data.fileName.orEmpty()
    is ElementDto.VideoDto -> data.fileName.orEmpty()
    is ElementDto.SpacerDto -> "${data.value}"
    else -> "${children().size} item(s)"
}
