package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import kotlinx.serialization.json.Json

actual class SignalRService actual constructor(
    private val preferences: SharedPreferencesProvider,
    private val serverConnection: IServerConnection,
    private val appLogger: AppLogger
) {
    private var hubConnection: HubConnection? = null
    private val json = Json { ignoreUnknownKeys = true }

    actual fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        cleanup()

        val signalRIp = preferences.get(Constants.TERMINAL_IP, "192.168.209.14")
        val terminalPort = preferences.get(Constants.TERMINAL_PORT, 9011)
        val terminalApi = preferences.get(Constants.TERMINAL_API, "signalr")
        val signalRUri = "http://$signalRIp:$terminalPort/$terminalApi"

        appLogger.trackLog("SIGNALR", "Connecting to $signalRUri")

        val connection = HubConnectionBuilder()
            .withUrl(signalRUri)
            .withAutomaticReconnect()
            .build()

        connection.on("SendDto") { data ->
            val jsonResult = JsonGlobal.stringify(data)
            appLogger.trackLog("connection DATA-Response: ", jsonResult)
            try {
                val responseDto = json.decodeFromString<TerminalResponseDto>(jsonResult)
                onSignalRResult(ServiceResult.Success(responseDto))
            } catch (e: Exception) {
                appLogger.trackError(e)
            }
        }

        /*
         *  We use the JS SignalR library's built-in reconnection hooks
         *  (onreconnecting / onreconnected / onclose) to drive the connection
         *  status, rather than porting Android's manual polling loop. This is
         *  an intentional behavioural difference — the JS SDK manages retries
         *  internally via withAutomaticReconnect(), so no explicit polling is
         *  needed.
         */
        connection.onreconnecting {
            appLogger.trackLog("signalR", "Reconnecting...")
            serverConnection.setStatusConnection(false)
        }

        connection.onreconnected { connectionId ->
            appLogger.trackLog("signalR", "Reconnected as $connectionId")
            serverConnection.setStatusConnection(true)
        }

        connection.onclose { error ->
            appLogger.trackLog("signalR", if (error != null) "Connection closed with error" else "Connection closed")
            serverConnection.setStatusConnection(false)
        }

        connection.start().`catch` { error ->
            appLogger.trackLog("signalR-start-error", error.toString())
            serverConnection.setStatusConnection(false)
        }

        hubConnection = connection
    }

    actual fun cleanup() {
        try {
            hubConnection?.stop()
            hubConnection = null
        } catch (e: Exception) {
            appLogger.trackError(e)
        }
    }
}
