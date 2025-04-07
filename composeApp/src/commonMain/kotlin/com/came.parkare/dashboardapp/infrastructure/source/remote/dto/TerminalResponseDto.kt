package com.came.parkare.dashboardapp.infrastructure.source.remote.dto

import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DitResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DitTypeResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class TerminalResponseDto(
    @SerialName("Dialog")
    val dialog: DialogResponseDto,
    @SerialName("Dits")
    val ditsTUI: JsonArray?,
    @SerialName("DtoType")
    val dtoType: TypeResponseDto,
    @SerialName("DtoVersion")
    val dtoVersion: Int,
    @SerialName("TerminalNr")
    val terminalNr: Int
)

fun mapDitsJsonToModel(ditsTUI: JsonArray?): List<DitResponseDto> {
    if (ditsTUI == null) return emptyList()
    val dits = mutableListOf<DitResponseDto>()

    // Iterate over the Dits array
    for (ditElement in ditsTUI) {
        if (ditElement is JsonNull) continue
        if (ditElement !is JsonObject) continue

        // Extract the known fields
        val ditType = Json.decodeFromJsonElement<DitTypeResponseDto>(ditElement["DitType"] ?: continue)
        val version = ditElement["Version"]?.jsonPrimitive?.intOrNull ?: continue

        // Create a new JsonObject to hold additional properties
        val additionalProperties = buildJsonObject {
            for ((key, value) in ditElement) {
                if (key != "DitType" && key != "Version") {
                    put(key, value)
                }
            }
        }

        // Create the Dit object
        val dit = DitResponseDto(ditType, version, additionalProperties)
        dits.add(dit)
    }
    return dits
}