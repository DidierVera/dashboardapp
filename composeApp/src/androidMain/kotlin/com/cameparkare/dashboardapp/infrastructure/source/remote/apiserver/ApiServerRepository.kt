package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import com.cameparkare.dashboardapp.domain.models.DashboardIpModel
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.TerminalConnectionModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto

interface ApiServerRepository {
    suspend fun saveDashboardIp(dashboardIp: String, customName: String): Int
    suspend fun saveScreensConfig(screensConfig: List<ScreenModel>): Boolean
    suspend fun getDashboardIpList(): List<DashboardIpModel>
    suspend fun saveTerminalConnection(config: TerminalConnectionModel)
    suspend fun getCurrentConfiguration(): List<ScreenDto>
}