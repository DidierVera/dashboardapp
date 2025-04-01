package com.cameparkare.dashboardapp.domain.repositories.external

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.models.ConnectionConfigModel
import com.cameparkare.dashboardapp.domain.models.ScreenModel

interface ConfigFileRepository {
    suspend fun getConnectionConfig(): ServiceResult<Boolean>
    suspend fun getFileConfiguration(): ServiceResult<Boolean>
    suspend fun writeConnectionConfig(newData: ConnectionConfigModel): ServiceResult<Boolean>
    suspend fun writeScreensConfig(newData: List<ScreenModel>): ServiceResult<Boolean>
}