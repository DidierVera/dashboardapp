package com.cameparkare.dashboardapp.ui.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import kotlinx.serialization.json.JsonArray


data class MainState (
    val currentLang: String = "",
    val newItems: List<ElementModel> = emptyList(),
    val ditsUI: Map<String, String>? = null,
    val translations: List<String> = emptyList(),
    val statusConnection: Boolean = false,
    val textSizeScale: Int = 10,
    val contentPadding: PaddingValues = PaddingValues(8.dp,8.dp,8.dp,8.dp)
)

data class VideoPlayerState(val videoRoutes: List<String>, val currentIndex: Int = 0)
