package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDto
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlin.random.Random

class MockSignalRService(
    private val preferences: SharedPreferencesProvider,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
) {
    fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        appLogger.trackLog("MockSignalR", "Mock connection started")
        serverConnection.setStatusConnection(true)

        val fakeResponse = TerminalResponseDto(
            dialog = DialogResponseDto(
                dialogName = "IDLE",
                dialogNumber = 5
            ),
            dtoVersion = 0,
            terminalNr = 2,
            dtoType = TypeResponseDto(
                dtoType = 0,
                dtoName = "DtoDialog"
            ),
            ditsTUI = buildJsonArray {
                add(buildJsonObject {
                    put("DitType", buildJsonObject {
                        put("DitType", JsonPrimitive(18))
                        put("DitName", JsonPrimitive("dit_IssuerStatus"))
                    })
                    put("Version", JsonPrimitive(0))
                    put("Status", JsonPrimitive(Random.nextInt(0, 2)))
                })
                add(buildJsonObject {
                    put("DitType", buildJsonObject {
                        put("DitType", JsonPrimitive(19))
                        put("DitName", JsonPrimitive("dit_ReaderStatus"))
                    })
                    put("Version", JsonPrimitive(0))
                    put("Status", JsonPrimitive(Random.nextInt(0, 2)))
                })
            }
        )

        onSignalRResult(ServiceResult.Success(fakeResponse))
    }

    fun cleanup() {
        appLogger.trackLog("MockSignalR", "Mock cleanup")
        serverConnection.setStatusConnection(false)
    }
}
