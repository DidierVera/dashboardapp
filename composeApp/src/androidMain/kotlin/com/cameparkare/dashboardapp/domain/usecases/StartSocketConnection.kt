package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.terminal.TerminalResponseModel
import com.cameparkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository

class StartSocketConnection(
    private val terminalConnectionRepository: TerminalConnectionRepository,
    private val appLogger: AppLogger
) {
    fun invoke(connectionType: TypeConnectionEnum, onResult: (ServiceResult<TerminalResponseModel>) -> Unit){
        try {
            terminalConnectionRepository.openConnection(connectionType) {
                onResult.invoke(it)
            }
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }
}