package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

class GetScreensConfig(
    private val screenRepository: ScreenRepository
) {
    suspend fun invoke(ipAddress: String): ServiceResult<List<ScreenDto>>{
        val result = screenRepository.getScreens(ipAddress)
        return result
    }
}