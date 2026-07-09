package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto

actual class SignalRService actual constructor(
    preferences: SharedPreferencesProvider,
    serverConnection: IServerConnection,
    appLogger: AppLogger
) {
    actual fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit) {
        throw NotImplementedError("Windows SignalR transport not yet implemented")
    }

    actual fun cleanup() {
        throw NotImplementedError("Windows SignalR transport not yet implemented")
    }
}
