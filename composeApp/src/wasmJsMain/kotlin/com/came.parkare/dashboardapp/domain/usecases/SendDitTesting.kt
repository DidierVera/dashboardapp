package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.testing.SendDitTestingDto
import kotlinx.browser.window

class SendDitTesting(
    private val screenRepository: ScreenRepository,
    private val preferences: WasmSharedPreferencesProvider,
) {
    suspend fun invoke(data: SendDitTestingDto): ServiceResult<ResponseStatusDto> {
        val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
        return screenRepository.sendDitTesting(ipAddress, data)
    }
}
