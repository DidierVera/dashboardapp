package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import kotlinx.browser.window

class GetScreensConfig(
    private val preferences: WasmSharedPreferencesProvider,
    private val screenRepository: ScreenRepository
) {
    suspend fun invoke(): ServiceResult<List<ScreenDto>>{
        val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
        val result = screenRepository.getScreens(ipAddress)
        return result
    }
}