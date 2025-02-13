package com.cameparkare.dashboardapp.infrastructure.source.remote.dto
import com.cameparkare.dashboardapp.domain.models.TerminalResponseModel
//import com.google.gson.JsonArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

data class TerminalResponseDto(
    @SerialName("Dialog")
    val dialog: DialogResponseDto,
    @SerialName("Dits")
    val ditsTUI: JsonArray,
    @SerialName("DtoType")
    val dtoType: TypeResponseDto,
    @SerialName("DtoVersion")
    val dtoVersion: Int,
    @SerialName("TerminalNr")
    val terminalNr: Int
)


fun TerminalResponseDto.toModel(): TerminalResponseModel {
    return TerminalResponseModel(
        dispatcherCode = dialog.dialogNumber,
        ditsTUI =  ditsTUI.toMapOrNull()
    )
}

// Función de extensión para convertir JsonArray a Map<String, String>?
fun JsonArray.toMapOrNull(): Map<String, String>? {
    return this.mapNotNull { jsonElement ->
        jsonElement.jsonObject.entries.firstOrNull()?.let { entry ->
            entry.key to entry.value.jsonPrimitive.content
        }
    }.toMap().takeIf { it.isNotEmpty() }
}