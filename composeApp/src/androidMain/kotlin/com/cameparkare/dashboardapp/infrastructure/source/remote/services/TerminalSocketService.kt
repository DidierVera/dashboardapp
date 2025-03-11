package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import android.util.Log
import com.cameparkare.dashboardapp.config.constants.Constants.SOCKET_URL
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    fun startConnection(onSocketResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        val serverIp = preferences.get(SOCKET_URL, "192.168.209.62")
        val serverPort = preferences.get(TERMINAL_PORT, "9010")
        CoroutineScope(Dispatchers.IO).launch {
            Thread {
                try {
                    Log.d("com.came.parkare.dashboardapp.Socket", "Inicio de aplicación conexión SOCKET")

                    val socket = Socket(serverIp, serverPort.toInt())
                    Log.d("com.came.parkare.dashboardapp.Socket", "Conexión con la ip y el puerto")

                    val input = BufferedReader(InputStreamReader(socket.getInputStream()))
                    Log.d("com.came.parkare.dashboardapp.Socket", "Obtención de la información del socket input")
                    //val output = PrintWriter(socket.getOutputStream(), true)

                    val buffer = CharArray(100000)

                    while (true) {
                        if(socket.isConnected) serverConnection.setStatusConnection(socket.isConnected)

                        val st = input.read(buffer);
                        if (st != -1) {
                            val serverMessage = String(buffer, 0, st);
                            Log.d("Socket", "Start message ${serverMessage}")
                            try {
                                val data = Json.decodeFromString<TerminalResponseDto>(serverMessage)
                                onSocketResult.invoke(ServiceResult.Success(data))
                            }catch (e: Exception){
                                appLogger.trackError(e)
                            }
                        } else {
                            Log.d("com.came.parkare.dashboardapp.Socket", "ruptura por falta de  información")
                            onSocketResult.invoke(ServiceResult.Error(ErrorTypeClass.NotSocketResponse))
                            Thread.sleep(12000)
                        }
                    }
                } catch (e: Exception) {
                    serverConnection.setStatusConnection(false)
                    appLogger.trackError(e)
                }
            }.start()
        }
    }
}