package com.came.parkare.dashboardapp.ui.utils

/**
 * Starts a server on [port] and, if it fails (usually because the port is busy), tries once more
 * on [defaultPort]. Returns the port where the server is listening, or null if it did not start.
 * [onMessage] receives the messages to show to the technician.
 */
inline fun startWithFallback(
    serverName: String,
    port: Int,
    defaultPort: Int,
    onMessage: (String) -> Unit,
    start: (Int) -> Boolean
): Int? {
    if (start(port)) return port

    if (port != defaultPort && start(defaultPort)) {
        onMessage("$serverName: port $port is busy, using $defaultPort")
        return defaultPort
    }

    onMessage("$serverName could not start on port $port")
    return null
}
