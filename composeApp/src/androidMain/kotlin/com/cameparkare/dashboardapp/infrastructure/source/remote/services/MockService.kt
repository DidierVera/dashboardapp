package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement


class MockService (
    private val serverConnection: IServerConnection,
    private val logger: AppLogger
) {
    fun startConnection(onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {

            Thread {
                try {
                    logger.trackLog("com.came.parkare.dashboardapp.Mock", "Inicio de aplicación conexión MOCK")
                    while (true){
                        serverConnection.setStatusConnection(true)
                        //Thread.sleep((1000L..8000).random())
                        //IDLE (rest screen)
                        var result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 0,
                                dialogName = "DLG_BOOT"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())
                        //IDLE (rest screen)
                        result = TerminalResponseDto(
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
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())
                        //MOCK DISCONNECTED(rest screen)
                        serverConnection.setStatusConnection(false)
                        //Thread.sleep((1000L..8000).random())
                        serverConnection.setStatusConnection(true)
                        //DLG_OUT_SERVICE
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 6,
                                dialogName = "DLG_OUT_SERVICE"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())
                        //DLG_PARKING_COMPLETED
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 7,
                                dialogName = "DLG_PARKING_COMPLETED"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())

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
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())

                        //PLEASE PROCEDURE
                        val jsonArray = getPleaseProceedJsonDit()
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
                        Thread.sleep((4000L..8000).random())
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
                            ditsTUI = jsonArray
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        Thread.sleep((1000L..8000).random())
                        //DLG_CARD_ERROR (rest screen)
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
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((4000L..10000).random())
                        //DLG_PAYMENT_REQUIRED (pendiente de pago)
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 36,
                                dialogName = "DLG_PAYMENT_REQUIRED"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())
                        //DLG_InicioCobroActual (pendiente de pago)
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 89,
                                dialogName = "DLG_InicioCobroActual"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())
                        //DLG_LOCKED
                        result = TerminalResponseDto(
                            dialog = DialogResponseDto(
                                dialogNumber = 96,
                                dialogName = "DLG_LOCKED"
                            ),
                            dtoVersion = 0,
                            terminalNr = 2,
                            dtoType = TypeResponseDto(
                                dtoType = 0,
                                dtoName = "DtoDialog"
                            ),
                            ditsTUI = null
                        )
                        //onSocketResult.invoke(ServiceResult.Success(result))
                        //Thread.sleep((1000L..8000).random())
                    }

                }catch (e: Exception){
                    logger.trackError(e)
                    e.printStackTrace()
                }
            }.start()
        }
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
                    "Status": ${(0..1).random()}
                },
                {
                    "DitType": {
                        "DitType": 19,
                        "DitName": "dit_ReaderStatus"
                    },
                    "Version": 0,
                    "Status": ${(0..1).random()}
                }
            ]
        """.trimIndent()
        return Json.decodeFromString(jsonString)
    }

    private fun getPleaseProceedJsonDit(): JsonArray {
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
        return Json.decodeFromString(jsonString)
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