package com.cameparkare.dashboardapp.infrastructure.source.external.dto.device

import com.cameparkare.dashboardapp.domain.models.DeviceModel
import kotlinx.serialization.Serializable

@Serializable
data class DeviceDto(
    val id: Long = 0,
    val deviceIp: String,
    val customName: String,
    val terminalIp: String? = null
)

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