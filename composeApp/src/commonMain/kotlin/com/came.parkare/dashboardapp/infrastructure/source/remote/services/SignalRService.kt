package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto

expect class SignalRService(
    preferences: SharedPreferencesProvider,
    serverConnection: IServerConnection,
    appLogger: AppLogger
) {
    fun startConnection(onSignalRResult: (ServiceResult<TerminalResponseDto>) -> Unit)
    fun cleanup()
}
