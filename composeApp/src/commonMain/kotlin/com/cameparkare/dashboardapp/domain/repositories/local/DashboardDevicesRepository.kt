package com.cameparkare.dashboardapp.domain.repositories.local

import com.cameparkare.dashboardapp.domain.models.DeviceModel

interface DashboardDevicesRepository {
    suspend fun getDevicesList(): List<DeviceModel>
    suspend fun saveDevice(device: DeviceModel)
    suspend fun deleteDevice(id: Long)
    suspend fun updateDeviceInfo(id: Long, newData: DeviceModel)
}