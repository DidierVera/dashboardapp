package com.cameparkare.dashboardapp.infrastructure.repositories.local

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.DeviceModel
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardDevicesRepository
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.DashboardDeviceDao
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toModel

class DashboardDeviceRepositoryImpl(
    private val logger: AppLogger,
    private val dashboardDeviceDao: DashboardDeviceDao
): DashboardDevicesRepository {
    override suspend fun getDevicesList(): List<DeviceModel> {
        return try {
            val result = dashboardDeviceDao.getAll()
            return result.map { it.toModel() }
        }catch (e: Exception){
            logger.trackError(e)
            emptyList()
        }
    }

    override suspend fun saveDevice(device: DeviceModel) {
        try {
            val entity = device.toEntity()
            dashboardDeviceDao.insert(entity)
        }catch (e: Exception){
            logger.trackError(e)
        }
    }

    override suspend fun deleteDevice(id: Long) {
        try {
            val entity  = dashboardDeviceDao.getById(id)
            if (entity != null){
                dashboardDeviceDao.delete(entity)
            }
        }catch (e: Exception){
            logger.trackError(e)
        }
    }

    override suspend fun updateDeviceInfo(id: Long, newData: DeviceModel) {
        try {
            val oldEntity = dashboardDeviceDao.getById(id)
            if(oldEntity != null){
                dashboardDeviceDao.update(
                    oldEntity.copy(
                        deviceIp = newData.deviceIp,
                        customName = newData.customName,
                        terminalIp = newData.terminalIp
                    )
                )
            }else {
                dashboardDeviceDao.insert(
                    newData.toEntity().copy(id = 0)
                )
            }
        }catch(e: Exception) {
            logger.trackError(e)
        }
    }
}