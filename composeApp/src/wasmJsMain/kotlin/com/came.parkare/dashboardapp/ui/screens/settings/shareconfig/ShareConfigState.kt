package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import com.came.parkare.dashboardapp.domain.models.DeviceModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel

data class ShareConfigState(
    val isLoading: Boolean = false,
    val dashboardList: List<DeviceItemState> = emptyList(),
    val configToShare: List<ScreenModel> = emptyList(),
    val configType: String = ""

)

data class DeviceItemState(
    val dashboardIp: String,
    val dashboardName: String? = "------",
    val isActive: Boolean = false,
    val isCheckingStatus: Boolean = true,
    val isChecked: Boolean = false
)