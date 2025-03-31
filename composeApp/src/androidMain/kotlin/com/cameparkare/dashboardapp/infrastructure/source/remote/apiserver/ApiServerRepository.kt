package com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver

import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.ScreenDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.ConnectionConfigDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device.DeviceDto

interface ApiServerRepository {
    suspend fun saveDashboardIp(device: DeviceDto): Int
    suspend fun saveScreensConfig(screensConfig: List<ScreenDto>): Int
    suspend fun getDashboardIpList(): List<DeviceDto>
    suspend fun updateDashboardDeviceInfo(id: Long, newData: DeviceDto): Int
    suspend fun deleteDashboardDevice(id: Long): Int
    suspend fun saveTerminalConnection(data: ConnectionConfigDto): Int
    suspend fun getCurrentConfiguration(): List<ScreenDto>
}