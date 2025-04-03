package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.config.constants.Constants
import com.cameparkare.dashboardapp.domain.models.DeviceModel
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.device.DeviceDto
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames

@Entity(tableName = RoomTableNames.DASHBOARD_DEVICE_TABLE_NAME)
data class DashboardDeviceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val deviceIp: String,
    val customName: String,
    val terminalIp: String? = null
)


fun DashboardDeviceEntity.toModel(): DeviceModel{
    return DeviceModel(
        id = id, deviceIp = deviceIp, customName = customName, terminalIp = terminalIp
    )
}
fun DeviceModel.toEntity(): DashboardDeviceEntity{
    return DashboardDeviceEntity(
        id = id, deviceIp = deviceIp, customName = customName, terminalIp = terminalIp
    )
}

fun DeviceDto.toEntity(): DashboardDeviceEntity {
    return DashboardDeviceEntity(
        id, deviceIp, customName, terminalIp
    )
}
