package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.domain.models.components.ElementModel

interface UiUtils {
    suspend fun buildDashboardItem(items: List<ElementModel>, dits: Map<String, String>?, lang: String): List<ElementModel>
}