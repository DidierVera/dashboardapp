package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.models.terminal.TerminalResponseModel
import com.came.parkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository

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