package com.came.parkare.dashboardapp.domain.repositories.external

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.domain.models.ConnectionConfigModel
import com.came.parkare.dashboardapp.domain.models.ImagesFileModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ResourceFileDto

interface ConfigFileRepository {
    suspend fun getConnectionConfig(): ServiceResult<Boolean>
    suspend fun getFileConfiguration(): ServiceResult<Boolean>
    suspend fun writeConnectionConfig(newData: ConnectionConfigModel): ServiceResult<Boolean>
    suspend fun writeScreensConfig(newData: List<ScreenModel>): ServiceResult<Boolean>
    suspend fun getDefaultImages(): ServiceResult<Boolean>
    suspend fun writeImages(newData: List<ImagesFileModel>): ServiceResult<Boolean>
}