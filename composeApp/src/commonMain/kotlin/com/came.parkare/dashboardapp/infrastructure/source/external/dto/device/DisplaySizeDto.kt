package com.came.parkare.dashboardapp.infrastructure.source.external.dto.device

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Size of the screen of the monitor in dp (in its current orientation), so the web configurator
 * can draw the preview of a screen with the same proportions and sizes as the monitor.
 */
@Serializable
data class DisplaySizeDto(
    @SerialName("width-dp") val widthDp: Int,
    @SerialName("height-dp") val heightDp: Int,
    @SerialName("density") val density: Float
) {
    companion object {
        // Used when the monitor does not answer (versions prior to 2.3.0): DG03-A1W55 in
        // portrait, 1080 x 1920 px at density 2
        val DEFAULT = DisplaySizeDto(widthDp = 540, heightDp = 960, density = 2f)
    }
}
