package com.came.parkare.dashboardapp.infrastructure.repositories.remote

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.domain.models.terminal.TerminalResponseModel
import com.came.parkare.dashboardapp.domain.models.terminal.toModel
import com.came.parkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.came.parkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.MockService
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.SignalRService
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.TerminalSocketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


class TerminalConnectionImpl(
    private val appLogger: AppLogger,
    private val socketService: TerminalSocketService,
    private val mockService: MockService,
    private val signalRService: SignalRService
): TerminalConnectionRepository {

    private val connectionScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var currentConnectionJob: Job? = null

    override fun openConnection(connection: TypeConnectionEnum, onResult: (ServiceResult<TerminalResponseModel>) -> Unit) {
        cleanup()
        restartConnection(connection, onResult)
    }

    private fun restartConnection(
        connectionType: TypeConnectionEnum,
        onResult: (ServiceResult<TerminalResponseModel>) -> Unit
    ) {
        // Cancel any existing connection
        currentConnectionJob?.cancel("Switching connection type")
        currentConnectionJob = null

        // Start new connection based on type
        currentConnectionJob = connectionScope.launch {
            when(connectionType) {
                TypeConnectionEnum.SOCKET -> {
                    socketService.startConnection { validateResult(it, onResult) }
                }
                TypeConnectionEnum.SIGNAL_R -> {
                    signalRService.startConnection { validateResult(it, onResult) }
                }
                TypeConnectionEnum.MOCK -> {
                    mockService.startConnection { validateResult(it, onResult) }
                }
            }
        }
    }
    private fun cleanup(){
        signalRService.cleanup()
        socketService.cleanup()
        mockService.cleanup()
    }

    private fun validateResult(
        result: ServiceResult<TerminalResponseDto>, onResult: (ServiceResult<TerminalResponseModel>) -> Unit) {
        when(result) {
            is ServiceResult.Error -> onResult.invoke(ServiceResult.Error(result.error))
            is ServiceResult.Success -> {
                //appLogger.trackLog("DESERIALIZE DATA: ", Json.encodeToString(result.data))
                onResult.invoke(ServiceResult.Success(result.data?.toModel()))
            }
        }
    }
}