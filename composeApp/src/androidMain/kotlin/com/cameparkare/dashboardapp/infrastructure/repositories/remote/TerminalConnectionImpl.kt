package com.cameparkare.dashboardapp.infrastructure.repositories.remote

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.domain.models.TerminalResponseModel
import com.cameparkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.TerminalResponseDto
import com.cameparkare.dashboardapp.infrastructure.source.remote.dto.toModel
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.SignalRService
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.SocketService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TerminalConnectionImpl(
    private val appLogger: AppLogger,
    private val socketService: SocketService,
    private val signalRService: SignalRService,
    private val serverConnection: IServerConnection
): TerminalConnectionRepository {
    override fun openConnection(onResult: (ServiceResult<TerminalResponseModel>) -> Unit) {
        when(serverConnection.typeConnection.value){
            TypeConnectionEnum.SOCKET -> socketService.startConnection { validateResult(it, onResult) }// socket connection
            TypeConnectionEnum.SIGNAL_R -> signalRService.startConnection { validateResult(it, onResult) } // signalR connection
            TypeConnectionEnum.MOCK -> {
//                    mockService.startConnection {
//                        validateResult(
//                            it,
//                            onResult
//                        )
//                    }
                }
        }
    }

    private fun validateResult(result: ServiceResult<TerminalResponseDto>,
                               onResult: (ServiceResult<TerminalResponseModel>) -> Unit){
        when(result){
            is ServiceResult.Error -> onResult.invoke(ServiceResult.Error(result.error))
            is ServiceResult.Success -> {
                appLogger.trackLog("DESERIALIZE DATA: ", Json.encodeToString(result.data))
                onResult.invoke(ServiceResult.Success(result.data?.toModel()))
            }
        }
    }
}