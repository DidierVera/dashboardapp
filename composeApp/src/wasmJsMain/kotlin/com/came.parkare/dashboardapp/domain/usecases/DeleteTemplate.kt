package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.repositories.template.ConfigTemplateRepository
import kotlinx.browser.window

class DeleteTemplate(
    private val configTemplateRepository: ConfigTemplateRepository,
    private val preferences: WasmSharedPreferencesProvider
) {
    suspend fun invoke(model: ConfigTemplateModel): ServiceResult<ResponseStatusDto> {
        val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
        val result = configTemplateRepository.deleteTemplate(ipAddress, model)
        return result
    }
}