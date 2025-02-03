package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.domain.models.Components.ElementModel
import kotlinx.serialization.json.JsonArray

interface UiUtils {
    suspend fun buildDashboardItem(items: List<ElementModel>, dits: JsonArray?, lang: String): List<ElementModel>
}