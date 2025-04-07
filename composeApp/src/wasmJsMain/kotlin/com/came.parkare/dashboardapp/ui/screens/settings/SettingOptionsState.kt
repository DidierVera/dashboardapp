package com.came.parkare.dashboardapp.ui.screens.settings

import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.compose_multiplatform
import dashboardapp.composeapp.generated.resources.connection_option
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class SettingOptionsState(
    val iconRes: DrawableResource = Res.drawable.compose_multiplatform,
    val nameRes: StringResource = Res.string.connection_option,
    val isSelected: Boolean = false
)
