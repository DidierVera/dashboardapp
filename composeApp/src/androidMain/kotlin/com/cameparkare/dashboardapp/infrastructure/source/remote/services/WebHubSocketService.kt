package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import com.cameparkare.dashboardapp.config.constants.Constants.SIGNAL_R_URI
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_API
import com.cameparkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.internal.wait
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class WebHubSocketService(
    private val preferences: SharedPreferencesProvider,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
) {
    private lateinit var hubConnection: HubConnection
    fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit){
        println("SIGNALR == Inicio de aplicación conexión SIGNAL R")

        val signalRIp = preferences.get(SIGNAL_R_URI, "192.168.209.14")
        val terminalPort = preferences.get(TERMINAL_PORT, "9011")
        val terminalApi = preferences.get(TERMINAL_API, "signalr")
        val signalRUri = "http://$signalRIp:$terminalPort/$terminalApi"

        CoroutineScope(Dispatchers.IO).launch {

            try {
                hubConnection =
                    HubConnectionBuilder.create(signalRUri).build();

                hubConnection.on("SendDto", { result ->

                    appLogger.trackLog("connection DATA-Response: ", Json.encodeToString(result))
                    onSignalRResult.invoke(ServiceResult.Success(result))
                }, TerminalResponseDto::class.java)

                appLogger.trackLog("dashboardapp.signalR-STATUS", hubConnection.connectionState.name)
                while (isActive){
                    if (hubConnection.connectionState != HubConnectionState.CONNECTED) {
                        appLogger.trackLog("signalR-STATUS", hubConnection.connectionState.name)
                        serverConnection.setStatusConnection(false)
                        hubConnection.start()
                    }else{
                        serverConnection.setStatusConnection(true)
                    }
                    delay(500)
                }

                hubConnection.onClosed {
                    appLogger.trackError(it)
                }

            } catch (e: Exception) {
                appLogger.trackError(e)
                appLogger.trackLog("error-com.came.parkare.dashboardapp.signalR", e.message.toString())
            }
        }
    }

    suspend fun sendData(methodName: String, vararg args: Any) {
        try {
            if (hubConnection.connectionState == HubConnectionState.CONNECTED) {
                hubConnection.send(methodName, *args)
                appLogger.trackLog("SignalR-Send", "Método: $methodName, Args: ${args.joinToString()}")
            } else {
                appLogger.trackError(Exception("Intento de enviar datos sin conexión"))
            }
        } catch (e: Exception) {
            appLogger.trackError(e)
        }
    }

    suspend fun sendDataInvokeWithResult(methodName: String, resultType: Class<*>, vararg args: Any): Any? {
        return try {
            if (hubConnection.connectionState == HubConnectionState.CONNECTED) {
                val result = hubConnection.invoke(resultType, methodName, *args).wait()
                appLogger.trackLog("SignalR-Invoke", "Método: $methodName, Result: $result")
                result
            } else {
                appLogger.trackError(Exception("Intento de invocar método sin conexión"))
                null
            }
        } catch (e: Exception) {
            appLogger.trackError(e)
            null
        }
    }
}