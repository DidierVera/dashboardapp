package com.cameparkare.dashboardapp.domain.models.terminal

import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.mapDitsJsonToModel

data class TerminalResponseModel(
    val dispatcherCode: Int,
    val ditsTUI: List<DitResponseModel>?,
)

fun TerminalResponseDto.toModel(): TerminalResponseModel {
    return TerminalResponseModel(
        dispatcherCode = dialog.dialogNumber,
        ditsTUI = mapDitsJsonToModel(ditsTUI).map { it.toModel() }
    )
}