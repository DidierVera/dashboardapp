package com.cameparkare.dashboardapp.domain.repositories.external

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.models.ScreenModel

interface ConfigFileRepository {
    suspend fun getFileConfiguration(): ServiceResult<List<ScreenModel>>
}