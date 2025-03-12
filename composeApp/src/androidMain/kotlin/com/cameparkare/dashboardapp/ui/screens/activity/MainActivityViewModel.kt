package com.cameparkare.dashboardapp.ui.screens.activity

import androidx.lifecycle.ViewModel
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_APP_PASSWORD
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_APP_PORT
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_APP_USER
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PASSWORD
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PORT
import com.cameparkare.dashboardapp.config.constants.Constants.FTP_DEVICE_USER
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.ui.utils.FTPServer

class MainActivityViewModel (
    private val preferences: SharedPreferencesProvider,
    private val ftpServerConfig: FTPServer,
    private val ftpServerRoot: FTPServer
): ViewModel() {

    fun startDeviceFtpServer(rootDir: String){
        val port = preferences.get(FTP_DEVICE_PORT, 2121)
        val username = preferences.get(FTP_DEVICE_USER, "Parkare")
        val password = preferences.get(FTP_DEVICE_PASSWORD, "Parking2010")
        ftpServerConfig.startServer(port, username, password, rootDir)
    }

    fun startAppFtpServer(rootDir: String){
        val port = preferences.get(FTP_APP_PORT, 2122)
        val username = preferences.get(FTP_APP_USER, "admin")
        val password = preferences.get(FTP_APP_PASSWORD, "Parking2010")
        ftpServerRoot.startServer(port, username, password, rootDir)
    }

    fun stopFtpServers(){
        ftpServerConfig.stopServer()
        ftpServerRoot.stopServer()
    }
}