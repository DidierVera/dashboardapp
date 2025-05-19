package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import com.came.parkare.dashboardapp.domain.models.DeviceModel
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel

data class ShareConfigState(
    val isLoading: Boolean = false,
    val dashboardList: List<DeviceItemState> = emptyList(),
    val configToShare: List<ScreenModel> = emptyList(),
    val allowedToShare: Boolean = false,
    val screenViewer: String? = null,
    val configType: String = "",
    val textSizeScale: Int = 10,
    val elementsByScreen: List<ElementModel> = emptyList()

)

data class DeviceItemState(
    val dashboardIp: String,
    val dashboardName: String? = "------",
    val isActive: Boolean = false,
    val isCheckingStatus: Boolean = true,
    val isChecked: Boolean = false
)