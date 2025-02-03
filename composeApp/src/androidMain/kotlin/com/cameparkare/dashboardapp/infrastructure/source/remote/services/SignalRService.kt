package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import com.cameparkare.dashboardapp.config.Constants.SIGNAL_R_URI
import com.cameparkare.dashboardapp.config.Constants.TERMINAL_API
import com.cameparkare.dashboardapp.config.Constants.TERMINAL_PORT
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.google.gson.Gson
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class SignalRService(
    private val preferences: SharedPreferencesProvider,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
) {
    private lateinit var hubConnection: HubConnection
    fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit){
        println("com.came.parkare.dashboardapp.SIGNALR == Inicio de aplicación conexión SIGNAL R")

        val signalRIp = preferences.get(SIGNAL_R_URI, "192.168.209.192")
        val terminalPort = preferences.get(TERMINAL_PORT, "9011")
        val terminalApi = preferences.get(TERMINAL_API, "signalr")
        val signalRUri = "http://$signalRIp:$terminalPort/$terminalApi"

        CoroutineScope(Dispatchers.IO).launch {

            try {
                hubConnection =
                    HubConnectionBuilder.create(signalRUri).build();

                hubConnection.on("SendDto", { result ->

                    appLogger.trackLog("connection DATA-Response: ", Gson().toJson(result))
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
}