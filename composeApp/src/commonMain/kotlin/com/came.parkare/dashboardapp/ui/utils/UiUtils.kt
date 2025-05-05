package com.came.parkare.dashboardapp.ui.utils

import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.terminal.DitResponseModel

interface UiUtils {
    suspend fun buildDashboardItem(items: List<ElementModel>, dits: List<DitResponseModel>?, lang: String): List<ElementModel>
}