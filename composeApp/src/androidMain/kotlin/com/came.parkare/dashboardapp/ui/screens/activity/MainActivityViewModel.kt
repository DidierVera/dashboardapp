package com.came.parkare.dashboardapp.ui.screens.activity

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_APP_PASSWORD
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_APP_USER
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PASSWORD
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_DEVICE_USER
import com.came.parkare.dashboardapp.config.constants.DefaultPorts
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ResolvedServerPorts
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.usecases.LoadServerPorts
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.AndroidApiServer
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.AndroidApiServerFactory
import com.came.parkare.dashboardapp.ui.utils.FTPServer
import com.came.parkare.dashboardapp.ui.utils.startWithFallback
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainActivityViewModel (
    private val preferences: SharedPreferencesProvider,
    private val ftpServerConfig: FTPServer,
    private val ftpServerRoot: FTPServer,
    private val androidApiServerFactory: AndroidApiServerFactory,
    private val loadServerPorts: LoadServerPorts,
    private val appLogger: AppLogger
): ViewModel() {

    private var androidApiServer: AndroidApiServer? = null

    suspend fun loadServerPorts(): ResolvedServerPorts = loadServerPorts.invoke()

    /** Returns the port where the server is listening, or null if it did not start. */
    suspend fun startDeviceFtpServer(rootDir: String, port: Int, showMessage: (String) -> Unit): Int? =
        withContext(Dispatchers.IO) {
            ftpServerConfig.stopServer()
            val username = preferences.get(FTP_DEVICE_USER, "Parkare")
            val password = preferences.get(FTP_DEVICE_PASSWORD, "Parking2010")
            startWithFallback("FTP device", port, DefaultPorts.FTP_DEVICE, { report(it, showMessage) }) { p ->
                ftpServerConfig.startServer(p, username, password, rootDir)
            }
        }

    /** Returns the port where the server is listening, or null if it did not start. */
    suspend fun startAppFtpServer(rootDir: String, port: Int, showMessage: (String) -> Unit): Int? =
        withContext(Dispatchers.IO) {
            ftpServerRoot.stopServer()
            val username = preferences.get(FTP_APP_USER, "admin")
            val password = preferences.get(FTP_APP_PASSWORD, "Parking2010")
            startWithFallback("FTP app", port, DefaultPorts.FTP_APP, { report(it, showMessage) }) { p ->
                ftpServerRoot.startServer(p, username, password, rootDir)
            }
        }

    /** Returns the port where the server is listening, or null if it did not start. */
    suspend fun startAndroidApiServer(port: Int, showMessage: (String) -> Unit): Int? =
        withContext(Dispatchers.IO) {
            // The previous instance (onStart runs again when the activity comes back) holds the port
            androidApiServer?.stop()
            androidApiServer = null
            // Small delay to ensure the port is released
            delay(500)

            startWithFallback("API", port, DefaultPorts.API, { report(it, showMessage) }) { p ->
                val server = androidApiServerFactory.create(p)
                try {
                    server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
                    androidApiServer = server
                    true
                } catch (e: Exception) {
                    appLogger.trackError(e)
                    false
                }
            }
        }

    fun logServerMessage(message: String) {
        appLogger.trackLog("SERVER_PORTS", message)
    }

    private suspend fun report(message: String, showMessage: (String) -> Unit) {
        appLogger.trackLog("SERVER_PORTS", message)
        withContext(Dispatchers.Main) { showMessage(message) }
    }

    fun stopServers(){
        ftpServerConfig.stopServer()
        ftpServerRoot.stopServer()
        androidApiServer?.stop()
        androidApiServer = null
    }
}
