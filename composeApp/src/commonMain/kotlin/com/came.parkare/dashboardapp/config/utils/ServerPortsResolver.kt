package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.config.constants.DefaultPorts

/**
 * Ports where the local servers of the dashboard listen.
 */
data class ServerPorts(
    val web: Int = DefaultPorts.WEB,
    val api: Int = DefaultPorts.API,
    val ftpDevice: Int = DefaultPorts.FTP_DEVICE,
    val ftpApp: Int = DefaultPorts.FTP_APP
)

data class ResolvedServerPorts(
    val ports: ServerPorts,
    val warnings: List<String>
)

/**
 * Validates the ports read from the configuration files. A port out of range, reserved or
 * already taken by another server is replaced by its default value and reported as a warning,
 * so a wrong value in a file never leaves the dashboard without its servers.
 */
object ServerPortsResolver {

    fun isValidPort(port: Int): Boolean =
        port in DefaultPorts.MIN_PORT..DefaultPorts.MAX_PORT && port !in DefaultPorts.RESERVED_PORTS

    fun resolve(configured: ServerPorts): ResolvedServerPorts {
        val warnings = mutableListOf<String>()
        val used = mutableSetOf<Int>()

        fun pick(name: String, value: Int, default: Int): Int {
            val valid = isValidPort(value)
            val repeated = value in used
            val result = when {
                valid && !repeated -> value
                default !in used -> {
                    val reason = if (!valid) "is not valid" else "is already used by another server"
                    warnings.add("$name: port $value $reason, using $default")
                    default
                }
                // The default is taken too: another server was configured with it. A repeated
                // port is kept (the server that starts later falls back when it finds it busy);
                // an invalid one can not be used, so the default is tried and reported if busy
                !valid -> {
                    warnings.add("$name: port $value is not valid and the default port $default is used by another server")
                    default
                }
                else -> {
                    warnings.add("$name: port $value is also configured for another server")
                    value
                }
            }
            used.add(result)
            return result
        }

        // The API goes first: the web configurator and the other dashboards depend on it
        val api = pick("API", configured.api, DefaultPorts.API)
        val web = pick("Web", configured.web, DefaultPorts.WEB)
        val ftpDevice = pick("FTP device", configured.ftpDevice, DefaultPorts.FTP_DEVICE)
        val ftpApp = pick("FTP app", configured.ftpApp, DefaultPorts.FTP_APP)

        return ResolvedServerPorts(ServerPorts(web, api, ftpDevice, ftpApp), warnings)
    }
}
