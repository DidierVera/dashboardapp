package com.came.parkare.dashboardapp.ui.screens.activity

import androidx.lifecycle.ViewModel
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

    fun initAndroidServer(showMessage: (String) -> Unit){
        try {
            androidApiServer.start()
            val ipAddress = ConfigUI.getEthernetIpAddress() // Utiliza una función para obtener la IP del dispositivo
            val ipByWifi = ConfigUI.getWifiIpAddress()
            val port = preferences.get(API_PORT, 2023)
            ipAddress?.let {
                showMessage.invoke("API iniciado en: http://$it:$port")
            }
            ipByWifi?.let {
                showMessage.invoke("API iniciado en: http://$it:$port")
            }
        } catch (e: Exception) {
            showMessage.invoke("Error al iniciar el servidor: ${e.message}")
        }
    }
}