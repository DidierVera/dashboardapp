package com.came.parkare.dashboardapp.ui.screens.settings.shareconfig

import com.came.parkare.dashboardapp.domain.models.DeviceModel

data class ShareConfigState(
    val isLoading: Boolean = false,
    val dashboardList: Map<DeviceModel, Boolean> = emptyMap()

)
