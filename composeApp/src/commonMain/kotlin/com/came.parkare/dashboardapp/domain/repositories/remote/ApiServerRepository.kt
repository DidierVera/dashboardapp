package com.came.parkare.dashboardapp.domain.repositories.remote

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackErrorDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackLogDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import org.koin.core.logger.MESSAGE

interface ApiServerRepository {
    suspend fun saveDashboardIp(device: DeviceDto): Int
    suspend fun saveScreensConfig(screensConfig: List<ScreenDto>): Int
    suspend fun getDashboardIpList(): List<DeviceDto>
    suspend fun updateDashboardDeviceInfo(id: Long, newData: DeviceDto): Int
    suspend fun deleteDashboardDevice(id: Long): Int
    suspend fun saveTerminalConnection(data: ConnectionConfigDto): Int
    suspend fun getCurrentConfiguration(): List<ScreenDto>
    suspend fun getCurrentConnectionConfig(): ConnectionConfigDto
    suspend fun saveScreenConfigType(type: Int): Boolean
    suspend fun getScreenConfigType(): Long
    suspend fun saveConfiguratorLog(dto: TrackLogDto): Int
    suspend fun saveConfiguratorException(dto: TrackErrorDto): Int
}