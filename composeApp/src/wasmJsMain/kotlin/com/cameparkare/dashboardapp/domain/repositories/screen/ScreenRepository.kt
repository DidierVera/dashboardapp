package com.cameparkare.dashboardapp.domain.repositories.screen

import com.cameparkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

interface ScreenRepository {
    suspend fun saveScreens(ipAddress: String, screens: List<ScreenDto>): ServiceResult<ResponseStatusDto>
    suspend fun getScreens(ipAddress: String): ServiceResult<List<ScreenDto>>
    suspend fun shareScreenConfig(ipDestinations: List<String>, screens: List<ScreenDto>): ServiceResult<ResponseStatusDto>
}