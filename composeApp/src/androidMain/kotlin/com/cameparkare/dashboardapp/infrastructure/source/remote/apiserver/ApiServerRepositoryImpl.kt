package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import com.cameparkare.dashboardapp.domain.models.DashboardIpModel
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.TerminalConnectionModel
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto

class ApiServerRepositoryImpl(
    private val dashboardElementRepository: DashboardElementRepository
): ApiServerRepository {
    override suspend fun saveDashboardIp(dashboardIp: String, customName: String): Int {
        TODO("Not yet implemented")
    }

    override suspend fun saveScreensConfig(screensConfig: List<ScreenModel>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getDashboardIpList(): List<DashboardIpModel> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTerminalConnection(config: TerminalConnectionModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentConfiguration(): List<ScreenDto> {
        return dashboardElementRepository.getAllScreens().map { it.toDto() }
    }
}