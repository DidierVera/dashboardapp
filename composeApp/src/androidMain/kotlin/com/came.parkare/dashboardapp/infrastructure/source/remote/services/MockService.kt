package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import android.util.Log
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.testing.DitTestingDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.testing.SendDitTestingDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlin.coroutines.cancellation.CancellationException

class MockService(
    private val serverConnection: IServerConnection,
    private val dashboardElementRepository: DashboardElementRepository,
    private val logger: AppLogger
) {
    private val mockScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var listenerJob: Job? = null

    private var externalEventFlow = MutableSharedFlow<SendDitTestingDto>(extraBufferCapacity = Channel.UNLIMITED)

    suspend fun dispatchMockEvent(dto: SendDitTestingDto) {
        logger.trackLog("MOCK_CASS", "try to send event")
        externalEventFlow.emit(dto)
    }

    fun startConnection(onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        cleanup()

        listenerJob = mockScope.launch {
            try {
                serverConnection.setStatusConnection(true)
                logger.trackLog("MockService", "Modo MOCK activo — esperando eventos del módulo web")

                // Fase 1: bootstrap con la primera pantalla
                val firstScreen = dashboardElementRepository.getAllScreens().firstOrNull()
                if (firstScreen != null) {
                    val bootstrapResponse = buildResponseFromCode(firstScreen.dispatcherCode)
                    onSocketResult.invoke(ServiceResult.Success(bootstrapResponse))
                } else {
                    onSocketResult.invoke(
                        ServiceResult.Error(ErrorTypeClass.GeneralException("No screens available"))
                    )
                    return@launch
                }

                // Fase 2: escuchar eventos externos indefinidamente
                externalEventFlow.collect { dto ->
                    if (!isActive) return@collect
                    logger.trackLog("MOCK_CASS", "externalEventFlow.collect")

                    val result = buildResponseFromDto(dto)
                    onSocketResult.invoke(ServiceResult.Success(result))
                }

            } catch (e: CancellationException) {
                // normal al llamar cleanup()
            } catch (e: Exception) {
                logger.trackError(e)
                onSocketResult.invoke(
                    ServiceResult.Error(ErrorTypeClass.GeneralException(e.message ?: "MockService error"))
                )
            }
        }
    }

    /**
     * Convierte un SendDitTestingDto (del módulo web) en un TerminalResponseDto
     * que el sistema ya sabe procesar.
     */

    private fun buildResponseFromDto(dto: SendDitTestingDto): TerminalResponseDto {
        val ditsJson = dto.dits.takeIf { it.isNotEmpty() }?.let { buildDitsJsonArray(it) }
        return TerminalResponseDto(
            dialog = DialogResponseDto(
                dialogNumber = dto.dispatchCode.toInt(),
                dialogName = resolveDialogName(dto.dispatchCode)
            ),
            dtoVersion = 0,
            terminalNr = 2,
            dtoType = TypeResponseDto(dtoType = 0, dtoName = "DtoDialog"),
            ditsTUI = ditsJson
        )
    }

    private fun buildResponseFromCode(code: Long): TerminalResponseDto = when (code) {
        0L    -> getBootDit()
        5L    -> getIdleDit()
        1005L -> setOfflineMode()
        6L    -> getOutServiceDit()
        7L    -> getParkingCompleteDit()
        9L    -> getReadingPlateDit()
        12L   -> getPleaseProceedDit()
        8L    -> getUserDit()
        18L   -> getCardErrorDit()
        36L   -> getPaymentRequiredDit()
        89L   -> getStartCurrentBillDit()
        else  -> getTerminalLockedDit()
    }

    /**
     * Convierte la lista de DitTestingDto en un JsonArray compatible
     * con el formato que ya espera mapDitsJsonToModel().
     */

    private fun buildDitsJsonArray(dits: List<DitTestingDto>): JsonArray {
        val jsonString = buildString {
            append("[")
            dits.forEachIndexed { index, dit ->
                append("{")
                append(""""DitType":{"DitType":${dit.ditTypeCode},"DitName":"${dit.ditName}"},""")
                append(""""Version":0""")
                dit.fields.forEach { (key, value) ->
                    append(""","$key":${coerceJsonValue(value)}""")
                }
                append("}")
                if (index < dits.lastIndex) append(",")
            }
            append("]")
        }
        return Json.decodeFromString(jsonString)
    }

    private fun coerceJsonValue(value: String): String =
        value.toBigDecimalOrNull()?.toPlainString() ?: """"$value""""

    private fun resolveDialogName(code: Long): String = when (code) {
        0L    -> "DLG_BOOT"
        5L    -> "IDLE"
        6L    -> "DLG_OUT_SERVICE"
        7L    -> "DLG_PARKING_COMPLETED"
        8L    -> "USER"
        9L    -> "DLG_READING_PLATE"
        12L   -> "DLG_PLEASE_PROCEED"
        18L   -> "DLG_CARD_ERROR"
        36L   -> "DLG_PAYMENT_REQUIRED"
        89L   -> "DLG_InicioCobroActual"
        96L   -> "DLG_LOCKED"
        1005L -> "IDLE_DISCONNECTED"
        else  -> "DLG_UNKNOWN_$code"
    }

    fun cleanup() {
        listenerJob?.cancel()
        listenerJob = null
        // Reemplazar el flow para descartar eventos de la sesión anterior
        externalEventFlow = MutableSharedFlow(extraBufferCapacity = Channel.UNLIMITED)
        serverConnection.setStatusConnection(false)
    }

    private fun getTerminalLockedDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
    }

    private fun getStartCurrentBillDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
            ditsTUI = getPaymentRequiredJsonDit()
        )
    }

    private fun getPaymentRequiredDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
            ditsTUI = getPaymentRequiredJsonDit()
        )
    }

    private fun getCardErrorDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
    }

    private fun getUserDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
            ditsTUI = getPleaseProceedJsonDit()
        )
    }

    private fun getPleaseProceedDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
            ditsTUI = getPleaseProceedJsonDit()
        )
    }

    private fun getReadingPlateDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
    }

    private fun getParkingCompleteDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
    }

    private fun getOutServiceDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
    }

    private fun setOfflineMode() : TerminalResponseDto {
        serverConnection.setStatusConnection(false)
        Thread.sleep((1000L..2500).random())
        serverConnection.setStatusConnection(true)
        return TerminalResponseDto(
            dialog = DialogResponseDto(
                dialogNumber = 1005,
                dialogName = "IDLE_DISCONNECTED"
            ),
            dtoVersion = 0,
            terminalNr = 2,
            dtoType = TypeResponseDto(
                dtoType = 0,
                dtoName = "DtoDialog"
            ),
            ditsTUI = null
        )
    }

    private fun getIdleDit(): TerminalResponseDto {
        return TerminalResponseDto(
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
    }

    private fun getBootDit(): TerminalResponseDto {
        return TerminalResponseDto(
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

    private fun getPaymentRequiredJsonDit(): JsonArray {
        val jsonString = """
            [
                {
                    "DitType": {
                        "DitType": 3,
                        "DitName": "dit_AmountToPay"
                    },
                    "Version": 0,
                    "AmountTotal": ${kotlin.random.Random.nextInt(5, 2998)},
                    "AmountAlreadyPayed": 0
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