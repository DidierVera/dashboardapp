package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import com.came.parkare.dashboardapp.config.constants.Constants.SIGNAL_R_URI
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_API
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_IP
import com.came.parkare.dashboardapp.config.constants.Constants.TERMINAL_PORT
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.signalRdtos.TerminalResponseDtoSignalR
import com.google.gson.Gson
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.internal.wait


class SignalRService(
    private val preferences: SharedPreferencesProvider,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
) {
    private val signalRScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var signalRJob: Job? = null

    private val cancellationMessage = "Connection stopped by user"
    private var hubConnection: HubConnection? = null

    fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        println("SIGNALR == Inicio de aplicación conexión SIGNAL R")

        // Cancel any existing connection first
        //cleanup()

        val signalRIp = preferences.get(TERMINAL_IP, "192.168.209.14")
        val terminalPort = preferences.get(TERMINAL_PORT, 9011)
        val terminalApi = preferences.get(TERMINAL_API, "signalr")
        val signalRUri = "http://$signalRIp:$terminalPort/$terminalApi"

        signalRJob = signalRScope.launch {
            try {
                hubConnection = HubConnectionBuilder.create(signalRUri).build()

                hubConnection?.on("SendDto", { result ->
                    val jsonResult = Gson().toJson(result)

                    appLogger.trackLog("connection DATA-Response: ", jsonResult)
                    try {
                        val responseDto = Json.decodeFromString<TerminalResponseDto> (jsonResult)
                        onSignalRResult.invoke(ServiceResult.Success(responseDto))

                    }catch (e: Exception){
                        appLogger.trackError(e)
                    }

                }, TerminalResponseDtoSignalR::class.java)

                appLogger.trackLog("dashboardapp.signalR-STATUS", hubConnection?.connectionState?.name)
                while (!Thread.currentThread().isInterrupted) {
                    if (hubConnection?.connectionState != HubConnectionState.CONNECTED) {
                        appLogger.trackLog("signalR-STATUS", hubConnection?.connectionState?.name)
                        serverConnection.setStatusConnection(false)
                        hubConnection?.start()
                    } else {
                        serverConnection.setStatusConnection(true)
                    }
                    delay(500)
                }

                hubConnection?.onClosed {
                    appLogger.trackError(it)
                }

            } catch (e: Exception) {
                if(e.message?.contains(cancellationMessage) == false){
                    appLogger.trackError(e)
                    appLogger.trackLog("error-com.came.parkare.dashboardapp.signalR", e.message.toString())
                }
            }
        }
    }

    fun cleanup() {
        try {
            if(hubConnection == null) return
            if(signalRJob != null){
                signalRJob?.cancel(cancellationMessage)
                signalRJob = null
                if (hubConnection?.connectionState == HubConnectionState.CONNECTED) {
                    hubConnection?.stop()
                }else {
                    hubConnection?.close()
                }

                hubConnection = null
            }
        } catch (e: Exception) {
            if (e.message?.contains(cancellationMessage) == false) {
                appLogger.trackError(e)
            }
        }
    }
}