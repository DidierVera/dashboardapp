package com.came.parkare.dashboardapp.infrastructure.source.remote.services.signalRdtos

import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDtoSignalR
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDtoSignalR


data class TerminalResponseDtoSignalR(
    val Dialog: DialogResponseDtoSignalR,
    val Dits: com.google.gson.JsonArray?,
    val DtoType: TypeResponseDtoSignalR,
    val DtoVersion: Int,
    val TerminalNr: Int
)