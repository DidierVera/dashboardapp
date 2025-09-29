package com.came.parkare.dashboardapp.ui.screens.settings.connection

import com.came.parkare.dashboardapp.ui.screens.settings.components.states.FilePickerDialogState

data class ConnectionState(
    val connectionWay: Pair<Int, String> = Pair(-1, "Select one"),
    val connectionWayOptions: List<Pair<Int, String>> = emptyList(),
    val terminalIp: String = "",
    val port: Int = 9011,
    val api: String = "signalr",
    val delayTime: Int = 5,
    val textSizeScale: Int = 10,
    val showVideoFrame: Boolean = false,
    val showBrightnessMode: Boolean = false,
    val brightnessDelay: Int = 2,
    val clearSelectedFiles: Boolean = false,
    val imagesResources: List<FilePickerDialogState> = emptyList()
)
