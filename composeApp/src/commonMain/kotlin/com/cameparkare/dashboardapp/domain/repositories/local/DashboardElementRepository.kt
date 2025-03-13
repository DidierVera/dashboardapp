package com.cameparkare.dashboardapp.domain.repositories.local

import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel

interface DashboardElementRepository {
    suspend fun saveScreens(items: List<ScreenModel>)
    suspend fun getScreenByDispatcher(dispatcherCode: Long): ScreenModel?
    suspend fun getAllScreens(): List<ScreenModel>
}