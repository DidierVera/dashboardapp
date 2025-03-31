package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.device

import com.cameparkare.dashboardapp.domain.models.DeviceModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.DashboardDeviceEntity
import kotlinx.serialization.Serializable

@Serializable
data class DeviceDto(
    val id: Long = 0,
    val deviceIp: String,
    val customName: String,
    val terminalIp: String? = null
)

fun DeviceDto.toEntity(): DashboardDeviceEntity {
    return DashboardDeviceEntity(
        id, deviceIp, customName, terminalIp
    )
}

fun DeviceDto.toModel(): DeviceModel {
    return DeviceModel(
        id, deviceIp, customName, terminalIp
    )
}

fun DeviceModel.toDto() : DeviceDto {
    return DeviceDto(
        id, deviceIp, customName, terminalIp
    )
}