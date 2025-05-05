package com.came.parkare.dashboardapp.domain.repositories.screen

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

interface ScreenRepository {
    suspend fun saveScreens(ipAddress: String, screens: List<ScreenDto>): ServiceResult<ResponseStatusDto>
    suspend fun getScreens(ipAddress: String): ServiceResult<List<ScreenDto>>
    suspend fun shareScreenConfig(ipDestinations: List<String>, screens: List<ScreenDto>): ServiceResult<ResponseStatusDto>
}