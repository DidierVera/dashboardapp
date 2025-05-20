package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.domain.repositories.template.ConfigTemplateRepository
import kotlinx.browser.window

class GetTemplatesConfig(
    private val repository: ConfigTemplateRepository,
    private val appLogger: AppLogger,
    private val preferences: SharedPreferencesProvider
) {
    suspend fun invoke(): ServiceResult<List<ConfigTemplateModel>> {
        try {
            val ipAddress = preferences.get(Constants.SELECTED_IP_ADDRESS, window.location.hostname)
            return repository.getDefaultTemplates(ipAddress)
        }catch (e: Exception){
            appLogger.trackError(e)
            return ServiceResult.Error(ErrorTypeClass.GeneralException(e.message))
        }
    }
}