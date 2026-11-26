package com.came.parkare.dashboardapp.config.constants

/**
 * Ports used when the configuration files do not define one, or define one that is not valid.
 */
object DefaultPorts {
    const val WEB = 8080
    const val API = 2023
    const val FTP_DEVICE = 2121
    const val FTP_APP = 2122

    // Ports under 1024 need root on Android
    const val MIN_PORT = 1024
    const val MAX_PORT = 65535

    // 5555 is the ADB over network port used by the installer
    val RESERVED_PORTS = setOf(5555)
}
