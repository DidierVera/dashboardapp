package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

class GetScreensConfig(
    private val screenRepository: ScreenRepository
) {
    suspend fun invoke(ipAddress: String): ServiceResult<List<ScreenDto>>{
        val result = screenRepository.getScreens(ipAddress)
        return result
    }
}