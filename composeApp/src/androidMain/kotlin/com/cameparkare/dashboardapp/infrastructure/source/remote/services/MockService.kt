package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.DialogResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TypeResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement


class MockService (
    private val logger: AppLogger
) {
    fun startConnection(onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {

            Thread {
                try {
                    logger.trackLog("com.came.parkare.dashboardapp.Mock", "Inicio de aplicación conexión MOCK")
                    while (true){
                        //IDLE (rest screen)
                        Thread.sleep(1250)
                        var result = TerminalResponseDto(
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
                            ditsTUI =  JsonArray(emptyList())
                        )
                        onSocketResult.invoke(ServiceResult.Success(result))
                        Thread.sleep(7000)
                        //READING_PLATE
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 9,
                                dialogName = "DLG_READING_PLATE"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = JsonArray(emptyList())
                        )
                        onSocketResult.invoke(ServiceResult.Success(result))
                        Thread.sleep(3500)

                        //PLEASE PROCEDURE
                        val jsonArray = getJsonDit()
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 12,
                                dialogName = "DLG_PLEASE_PROCEED"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = jsonArray
                        )
                        onSocketResult.invoke(ServiceResult.Success(result))
                        Thread.sleep(7000)
                        //USER (rest screen)
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 8,
                                dialogName = "USER"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = JsonArray(emptyList())
                        )
                        onSocketResult.invoke(ServiceResult.Success(result))
                        Thread.sleep(3500)
                        //USER (rest screen)
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 18,
                                dialogName = "DLG_CARD_ERROR"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = JsonArray(emptyList())
                        )
                        onSocketResult.invoke(ServiceResult.Success(result))
                        Thread.sleep(3500)
                    }

                }catch (e: Exception){
                    logger.trackError(e)
                    e.printStackTrace()
                }
            }.start()
        }
    }

    private fun getJsonDit(): JsonArray {
        val jsonString = """
                            [{
                                "DitType":
                                {
                                    "DitType": 7,
                                    "DitName": "dit_CurrentCardReader"
                                },
                                "Version": 0,
                                "CardReader": ${(2..4).random()}
                            },
                            {
                                "DitType":
                                {
                                    "DitType": 9,
                                    "DitName": "dit_CurrentCardType"
                                },
                                "Version": 0,
                                "CardClass": ${(0..1).random()},
                                "CardType": 100
                            },
                            {
                            	"DitType":
                            	{
                            		"DitType": 10,
                            		"DitName": "dit_CurrentLicensePlates"
                            	},
                            	"Version": 0,
                                "MainLicensePlate": "${getRandomString(4, 2)}${getRandomString(3, 1)}",
                                "CurrentLicencePlates":
                                    {
                                        "Version": 0,
                                        "MainLicensePlate": "9106LWW",
                                        "AuxiliaryLicensePlates":
                                        [
                                            {
                                                "LicensePlateType": 0,
                                                "LicensePlate": "9106LWW"
                                            },
                                            {
                                                "LicensePlateType": 1,
                                                "LicensePlate": "9106LWW"
                                            }
                                        ]
                                    }
                            }]
                        """.trimIndent()

        val jsonElement: JsonElement = Json.parseToJsonElement(jsonString)
        return jsonElement as JsonArray
        //return JsonParser.parseString(jsonString).asJsonArray
    }

    private fun getRandomString(length: Int, type: Int) : String {
        val allowedChars = when(type){
            1 -> ('A'..'Z').filter { it !in setOf('A', 'E', 'I', 'O', 'U') }
            2 -> ('0'..'9').toList()
            else -> ('a'..'z').filter { it !in setOf('a', 'e', 'i', 'o', 'u') }
        }
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}