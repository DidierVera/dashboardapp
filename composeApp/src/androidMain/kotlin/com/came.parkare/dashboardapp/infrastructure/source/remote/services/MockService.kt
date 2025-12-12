package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.DialogResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.common.TypeResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlin.coroutines.cancellation.CancellationException


class MockService (
    private val serverConnection: IServerConnection,
    private val dashboardElementRepository: DashboardElementRepository,
    private val logger: AppLogger
) {
    private var mockThread: Thread? = null
    private val mockScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var isRunning = false

    fun startConnection(onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        // Limpiar completamente antes de empezar
        cleanup()

        // Marcar como en ejecución
        isRunning = true
        mockScope.launch {
            try {
                println("DEBUG: Starting connection - isRunning: $isRunning")
                val screens = dashboardElementRepository.getAllScreens()
                println("Screens count: ${screens.size}")

                if (screens.isEmpty()) {
                    delay(1000)
                    val retryScreens = dashboardElementRepository.getAllScreens()
                    println("Retry screens count: ${retryScreens.size}")
                    if (retryScreens.isEmpty()) {
                        onSocketResult.invoke(ServiceResult.Error(ErrorTypeClass.GeneralException("No screens available")))
                        return@launch
                    }
                    if (isRunning) {
                        processScreens(retryScreens, onSocketResult)
                    }
                } else {
                    if (isRunning) {
                        processScreens(screens, onSocketResult)
                    }
                }
            } catch (e: CancellationException) {
                println("DEBUG: Coroutine cancelled normally")
            } catch (e: Exception) {
                logger.trackError(e)
                println("DEBUG: Error in startConnection: ${e.message}")
            }
        }
    }

    private fun processScreens(
        screens: List<ScreenModel>,
        onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit
    ) {
        // Verificar que no haya ya un hilo en ejecución
        if (mockThread?.isAlive == true) {
            println("DEBUG: Thread already running, skipping new thread creation")
            return
        }

        mockThread = Thread {
            try {
                logger.trackLog("com.came.parkare.dashboardapp.Mock", "Inicio de aplicación conexión MOCK")
                println("DEBUG: Mock thread started - Thread ID: ${Thread.currentThread().id}")

                // Usar una copia local para evitar problemas de concurrencia
                val localScreens = screens.toList()

                while (!Thread.currentThread().isInterrupted && isRunning) {
                    serverConnection.setStatusConnection(true)
                    Thread.sleep((8000..10000).random().toLong())

                    for(screenToShow in localScreens.indices) {
                        if (Thread.currentThread().isInterrupted || !isRunning) {
                            println("DEBUG: Thread interrupted or not running, breaking loop")
                            break
                        }

                        val code = localScreens[screenToShow].dispatcherCode
                        println("Code Screen to show: $screenToShow - Code: $code - Thread ID: ${Thread.currentThread().id}")

                        var result = getBootDit()
                        when(code){
                            0L -> result = getBootDit()
                            5L -> result = getIdleDit()
                            1005L -> setOfflineMode()
                            6L -> result = getOutServiceDit()
                            7L -> result = getParkingCompleteDit()
                            9L -> result = getReadingPlateDit()
                            12L -> result = getPleaseProceedDit()
                            8L -> result = getUserDit()
                            18L -> result = getCardErrorDit()
                            36L -> result = getPaymentRequiredDit()
                            89L -> result = getStartCurrentBillDit()
                            else -> result = getTerminalLockedDit()
                        }

                        onSocketResult.invoke(ServiceResult.Success(result))

                        if (Thread.currentThread().isInterrupted || !isRunning) break
                        Thread.sleep((4000..6000).random().toLong())
                    }

                    // Verificar nuevamente antes de continuar con el siguiente ciclo
                    if (Thread.currentThread().isInterrupted || !isRunning) {
                        println("DEBUG: Exiting main while loop")
                        break
                    }
                }
            } catch (e: InterruptedException) {
                println("DEBUG: Thread interrupted normally")
                Thread.currentThread().interrupt()
            } catch (e: Exception) {
                if (!Thread.currentThread().isInterrupted && isRunning) {
                    logger.trackError(e)
                    e.printStackTrace()
                }
            } finally {
                println("DEBUG: Thread finished - Thread ID: ${Thread.currentThread().id}")
            }
        }.apply {
            name = "MockService-Thread-${System.currentTimeMillis()}"
            start()
        }
    }

    fun cleanup() {
        println("DEBUG: Cleaning up - isRunning was: $isRunning")

        // 1. Cambiar el estado primero
        isRunning = false

        // 2. Interrumpir el hilo
        mockThread?.let { thread ->
            if (thread.isAlive) {
                thread.interrupt()
                try {
                    // Esperar un tiempo razonable para que termine
                    thread.join(1000)
                    if (thread.isAlive) {
                        println("DEBUG: Thread did not stop gracefully")
                    }
                } catch (e: InterruptedException) {
                    println("DEBUG: Join interrupted")
                }
            }
        }

        // 3. Limpiar referencia
        mockThread = null

        // 4. Cancelar corrutinas del scope (opcional, solo las nuevas)
        // mockScope.coroutineContext.cancelChildren()

        println("DEBUG: Cleanup completed")
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
            ditsTUI = null
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
            ditsTUI = null
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

    private fun setOfflineMode() {
        serverConnection.setStatusConnection(false)
        Thread.sleep((2000L..8000).random())
        serverConnection.setStatusConnection(true)
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