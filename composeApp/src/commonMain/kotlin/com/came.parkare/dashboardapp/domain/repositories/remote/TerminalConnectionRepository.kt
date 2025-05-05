package com.came.parkare.dashboardapp.domain.repositories.remote

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.domain.models.terminal.TerminalResponseModel

interface TerminalConnectionRepository {
    fun openConnection(connection: TypeConnectionEnum, onResult: (ServiceResult<TerminalResponseModel>) -> Unit)
}