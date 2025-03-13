package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetScreenByDispatcher (
    private val dashboardElementRepository: DashboardElementRepository,
    private val appLogger: AppLogger
) {
    suspend fun invoke(dispatcherCode: Long): ScreenModel? = withContext(Dispatchers.IO) {
        try {
            dashboardElementRepository.getScreenByDispatcher(dispatcherCode)
        }catch (e: Exception){
            appLogger.trackError(e)
            null
        }
    }
}