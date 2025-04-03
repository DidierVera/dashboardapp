package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

class SaveScreenConfig(
    private val screenRepository: ScreenRepository
) {
    suspend fun invoke(ipAddress: String, screens: List<ScreenDto>): ServiceResult<ResponseStatusDto>{
        return screenRepository.saveScreens(ipAddress, screens)
    }
}