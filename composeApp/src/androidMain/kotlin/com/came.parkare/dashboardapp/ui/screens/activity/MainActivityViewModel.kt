package com.came.parkare.dashboardapp.ui.screens.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_APP_PASSWORD
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_APP_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_APP_USER
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PASSWORD
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_DEVICE_USER
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.AndroidApiServer
import com.came.parkare.dashboardapp.ui.utils.ConfigUI
import com.came.parkare.dashboardapp.ui.utils.FTPServer
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel (
    private val preferences: SharedPreferencesProvider,
    private val ftpServerConfig: FTPServer,
    private val ftpServerRoot: FTPServer,
    private val androidApiServer: AndroidApiServer
): ViewModel() {

    fun startDeviceFtpServer(rootDir: String){
        ftpServerConfig.stopServer()
        val port = preferences.get(FTP_DEVICE_PORT, 2121)
        val username = preferences.get(FTP_DEVICE_USER, "Parkare")
        val password = preferences.get(FTP_DEVICE_PASSWORD, "Parking2010")
        ftpServerConfig.startServer(port, username, password, rootDir)
    }

    fun startAppFtpServer(rootDir: String){
        ftpServerRoot.stopServer()
        val port = preferences.get(FTP_APP_PORT, 2122)
        val username = preferences.get(FTP_APP_USER, "admin")
        val password = preferences.get(FTP_APP_PASSWORD, "Parking2010")
        ftpServerRoot.startServer(port, username, password, rootDir)
    }

    fun initAndroidServer(showMessage: (String) -> Unit) {
        try {
            // Add small delay to ensure port is released
            viewModelScope.launch(Dispatchers.IO) {
                Thread.sleep(500)

                val port = preferences.get(API_PORT, 2023)

                // Create new instance instead of reusing old one
                androidApiServer.stop()
                androidApiServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)

                val ipAddress = ConfigUI.getEthernetIpAddress()
                val ipByWifi = ConfigUI.getWifiIpAddress()
                withContext(Dispatchers.Main){
                    ipAddress?.let {
                        showMessage("API iniciado en: http://$it:$port")
                    }
                    ipByWifi?.let {
                        showMessage("API iniciado en: http://$it:$port")
                    }
                }
            }

        } catch (e: Exception) {
            showMessage("API: ${e.message ?: "Unknown error"}")
        }
    }

    fun stopServers(){
        ftpServerConfig.stopServer()
        ftpServerRoot.stopServer()
        androidApiServer.stop()
    }
}