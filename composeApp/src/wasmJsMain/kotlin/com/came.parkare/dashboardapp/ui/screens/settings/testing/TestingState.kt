package com.came.parkare.dashboardapp.ui.screens.settings.testing

import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto

data class TestingState(
    val screens: List<ScreenDto> = emptyList(),
    val selectedScreen: ScreenDto? = null,
    val isLoading: Boolean = false,
    val isSending: Boolean = false,
    val ditFormGroups: List<DitFormGroup> = emptyList(),
)

data class DitFormGroup(
    val ditTypeCode: Int,
    val ditName: String,
    val fields: List<DitFormField>,
)

data class DitFormField(
    val key: String,
    val value: String,
)
