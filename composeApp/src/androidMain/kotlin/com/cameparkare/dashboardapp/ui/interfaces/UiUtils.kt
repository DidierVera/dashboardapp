package com.cameparkare.dashboardapp.ui.interfaces

import com.cameparkare.dashboardapp.ui.models.UiElementModel
import com.google.gson.JsonArray

interface UiUtils {
    suspend fun buildDashboardItem(items: List<UiElementModel>, dits: JsonArray?, lang: String): List<UiElementModel>
}