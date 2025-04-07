package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

class SaveScreenConfig(
    private val screenRepository: ScreenRepository
) {
    suspend fun invoke(ipAddress: String, screens: List<ScreenDto>): ServiceResult<ResponseStatusDto>{
        return screenRepository.saveScreens(ipAddress, screens)
    }
}