package com.cameparkare.dashboardapp.infrastructure.source.remote.dto
import com.cameparkare.dashboardapp.domain.models.TerminalResponseModel
//import com.google.gson.JsonArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonArray

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
        ditsTUI =  ditsTUI
    )
}