package com.cameparkare.dashboardapp.config

import com.cameparkare.dashboardapp.domain.models.terminal.DitResponseModel
import com.cameparkare.dashboardapp.domain.models.terminal.toModel
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray


object DefaultDits {
    fun idleConnected(): List<DitResponseModel> {
        val dto = TerminalResponseDto(
            dialog = DialogResponseDto(
                dialogNumber = 5,
                dialogName = "IDLE"
            ),
            dtoVersion = 0,
            terminalNr = 2,
            dtoType = TypeResponseDto(
                dtoType = 0,
                dtoName = "DtoDialog"
            ),
            ditsTUI = getIdleJsonDit()
        )

        return dto.toModel().ditsTUI ?: emptyList()
    }

    private fun getIdleJsonDit(): JsonArray {
        val jsonString = """
            [
                {
                    "DitType": {
                        "DitType": 18,
                        "DitName": "dit_IssuerStatus"
                    },
                    "Version": 0,
                    "Status": 0
                },
                {
                    "DitType": {
                        "DitType": 19,
                        "DitName": "dit_ReaderStatus"
                    },
                    "Version": 0,
                    "Status": 0
                }
            ]
        """.trimIndent()
        return Json.decodeFromString(jsonString)
    }

}