package com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver

import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.remote.ApiServerRepository
import com.came.parkare.dashboardapp.ui.utils.ConfigUI
import fi.iki.elonen.NanoHTTPD

class ApiServerManager(
    private val preferences: SharedPreferencesProvider,
    private val apiServerRepository: ApiServerRepository,
    private val appLogger: AppLogger
) {
    private var serverInstance: AndroidApiServer? = null
    private var currentPort: Int = preferences.get(API_PORT, 2023)

    fun startServer(port: Int? = null): Boolean {
        try {
            // Stop existing server if running
            stopServer()

            // Use provided port or fallback to stored preference
            val newPort = port ?: currentPort
            serverInstance = AndroidApiServer(preferences, apiServerRepository, newPort)
            serverInstance?.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
            currentPort = newPort

            // Save the port if it was explicitly provided
            port?.let {
                preferences.put(API_PORT, it)
                currentPort = it
            }

            return true
        } catch (e: Exception) {
            appLogger.trackError(e)
            e.printStackTrace()
            return false
        }
    }

    fun stopServer() {
        serverInstance?.stop()
        serverInstance = null
    }

    fun getCurrentPort(): Int = currentPort

    fun isRunning(): Boolean = serverInstance?.isAlive == true

    fun getServerAddresses(): List<String> {
        val addresses = mutableListOf<String>()
        ConfigUI.getEthernetIpAddress()?.let {
            addresses.add("http://$it:$currentPort")
        }
        ConfigUI.getWifiIpAddress()?.let {
            addresses.add("http://$it:$currentPort")
        }
        return addresses
    }
}