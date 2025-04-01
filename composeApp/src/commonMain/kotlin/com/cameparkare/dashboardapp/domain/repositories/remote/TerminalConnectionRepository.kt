package com.cameparkare.dashboardapp.domain.repositories.remote

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.domain.models.terminal.TerminalResponseModel

interface TerminalConnectionRepository {
    fun openConnection(connection: TypeConnectionEnum, onResult: (ServiceResult<TerminalResponseModel>) -> Unit)
}