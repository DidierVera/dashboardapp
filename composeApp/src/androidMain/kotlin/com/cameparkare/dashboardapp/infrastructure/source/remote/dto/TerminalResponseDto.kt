package com.cameparkare.dashboardapp.infrastructure.source.remote.dto
import com.cameparkare.dashboardapp.domain.models.TerminalResponseModel
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class TerminalResponseDto(
    @SerializedName("Dialog")
    val dialog: DialogResponseDto,
    @SerializedName("Dits")
    val ditsTUI: JsonArray,
    @SerializedName("DtoType")
    val dtoType: TypeResponseDto,
    @SerializedName("DtoVersion")
    val dtoVersion: Int,
    @SerializedName("TerminalNr")
    val terminalNr: Int
)


fun TerminalResponseDto.toModel(): TerminalResponseModel {
    return TerminalResponseModel(
        dispatcherCode = dialog.dialogNumber,
        ditsTUI = ditsTUI
    )
}