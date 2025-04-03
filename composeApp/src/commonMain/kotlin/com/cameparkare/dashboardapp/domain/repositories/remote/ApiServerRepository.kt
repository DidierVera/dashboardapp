package com.cameparkare.dashboardapp.domain.repositories.remote

import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

interface ApiServerRepository {
    suspend fun saveDashboardIp(device: DeviceDto): Int
    suspend fun saveScreensConfig(screensConfig: List<ScreenDto>): Int
    suspend fun getDashboardIpList(): List<DeviceDto>
    suspend fun updateDashboardDeviceInfo(id: Long, newData: DeviceDto): Int
    suspend fun deleteDashboardDevice(id: Long): Int
    suspend fun saveTerminalConnection(data: ConnectionConfigDto): Int
    suspend fun getCurrentConfiguration(): List<ScreenDto>
    suspend fun getCurrentConnectionConfig(): ConnectionConfigDto
}