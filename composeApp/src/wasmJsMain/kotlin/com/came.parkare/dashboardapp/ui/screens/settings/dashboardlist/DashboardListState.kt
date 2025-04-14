package com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist

data class DashboardListState(
    val isSaveEnabled: Boolean = false,
    val id: Long = 0L,
    val dashboardIp: String = "",
    val customName: String? = "",
    val terminalIp: String? = "",
    val currentItems: List<DashboardListState> = emptyList()
)
