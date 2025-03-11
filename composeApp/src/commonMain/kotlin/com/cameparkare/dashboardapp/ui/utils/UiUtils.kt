package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.terminal.DitResponseModel

interface UiUtils {
    suspend fun buildDashboardItem(items: List<ElementModel>, dits: List<DitResponseModel>?, lang: String): List<ElementModel>
}