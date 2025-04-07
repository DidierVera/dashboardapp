package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import android.util.Log
import com.came.parkare.dashboardapp.config.constants.Constants.SOCKET_URL
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket


class TerminalSocketService(
    private val preferences: SharedPreferencesProvider,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
) {
    private var socketThread: Thread? = null
    private var socket: Socket? = null
    private var input: BufferedReader? = null
    private val socketScope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    fun startConnection(onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        //cleanup() // Clean up any existing connection

        val serverIp = preferences.get(SOCKET_URL, "192.168.209.62")
        val serverPort = preferences.get(TERMINAL_PORT, 9010)

        socketScope.launch {
            socketThread = Thread {
                try {
                    Log.d("com.came.parkare.dashboardapp.Socket", "Inicio de aplicación conexión SOCKET")
                    socket = Socket(serverIp, serverPort)
                    Log.d("com.came.parkare.dashboardapp.Socket", "Conexión con la ip y el puerto")

                    input = BufferedReader(InputStreamReader(socket?.getInputStream()))
                    Log.d("com.came.parkare.dashboardapp.Socket", "Obtención de la información del socket input")

                    val buffer = CharArray(100000)

                    while (!Thread.currentThread().isInterrupted) {
                        if (socket?.isConnected == true) {
                            serverConnection.setStatusConnection(true)
                        }

                        val st = input?.read(buffer) ?: -1
                        if (st != -1) {
                            val serverMessage = String(buffer, 0, st)
                            Log.d("Socket", "Start message ${serverMessage}")
                            try {
                                val data = Json.decodeFromString<TerminalResponseDto>(serverMessage)
                                onSocketResult.invoke(ServiceResult.Success(data))
                            } catch (e: Exception) {
                                appLogger.trackError(e)
                            }
                        } else {
                            Log.d("com.came.parkare.dashboardapp.Socket", "ruptura por falta de información")
                            onSocketResult.invoke(ServiceResult.Error(ErrorTypeClass.NotSocketResponse))
                            Thread.sleep(12000)
                        }
                    }
                } catch (e: Exception) {
                    if (!Thread.currentThread().isInterrupted) {
                        serverConnection.setStatusConnection(false)
                        appLogger.trackError(e)
                    }
                } finally {
                    cleanupResources()
                }
            }.apply { start() }
        }
    }

    fun cleanup() {
        socketThread?.interrupt()
        socketThread = null
        cleanupResources()
    }

    private fun cleanupResources() {
        try {
            input?.close()
        } catch (e: Exception) {
            appLogger.trackError(e)
        }
        input = null

        try {
            socket?.close()
        } catch (e: Exception) {
            appLogger.trackError(e)
        }
        socket = null
    }
}