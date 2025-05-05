package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
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