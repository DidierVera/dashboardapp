package com.came.parkare.dashboardapp.ui.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.ScreenModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.terminal.DitResponseModel
import kotlinx.serialization.json.JsonArray

data class MainState (
    val currentLang: String = "",
    val newItems: List<ElementModel> = emptyList(),
    val ditsUI: List<DitResponseModel>? = null,
    val translations: List<String> = emptyList(),
    val statusConnection: Boolean = false,
    val textSizeScale: Int = 10,
    val contentPadding: PaddingValues = PaddingValues(8.dp,8.dp,8.dp,8.dp),
    val screenList: List<ScreenModel> = emptyList()
)

data class VideoPlayerState(val videoRoutes: List<String>, val currentIndex: Int = 0)
