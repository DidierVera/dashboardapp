package com.cameparkare.dashboardapp.domain.repositories.remote

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.models.TerminalResponseModel

interface TerminalConnectionRepository {
    fun openConnection(onResult: (ServiceResult<TerminalResponseModel>) -> Unit)
}