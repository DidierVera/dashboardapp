package com.came.parkare.dashboardapp.domain.models.terminal

import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.mapDitsJsonToModel

data class TerminalResponseModel(
    val dispatcherCode: Long,
    val ditsTUI: List<DitResponseModel>?,
)

fun TerminalResponseDto.toModel(): TerminalResponseModel {
    return TerminalResponseModel(
        dispatcherCode = dialog.dialogNumber.toLong(),
        ditsTUI = mapDitsJsonToModel(ditsTUI).map { it.toModel() }
    )
}