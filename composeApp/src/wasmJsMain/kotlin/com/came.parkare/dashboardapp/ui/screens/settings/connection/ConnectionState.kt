package com.came.parkare.dashboardapp.ui.screens.settings.connection

import com.came.parkare.dashboardapp.ui.screens.settings.components.states.FilePickerDialogState

data class ConnectionState(
    val isLoading: Boolean = false,
    val connectionWay: String = "Select one",
    val connectionWayOptions: Map<Int, String> = emptyMap(),
    val terminalIp: String = "",
    val port: Int = 9011,
    val api: String = "signalr",
    val delayTime: Int = 5,
    val textSizeScale: Int = 10,
    val showVideoFrame: Boolean = false,
    val clearSelectedFiles: Boolean = false,
    val imagesResources: List<FilePickerDialogState> = emptyList()
)
