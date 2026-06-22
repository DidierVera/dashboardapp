package com.came.parkare.dashboardapp.ui.utils

import org.apache.ftpserver.FtpServer
import org.apache.ftpserver.FtpServerFactory
import org.apache.ftpserver.ftplet.Authority
import org.apache.ftpserver.ftplet.UserManager
import org.apache.ftpserver.listener.ListenerFactory
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory
import org.apache.ftpserver.usermanager.impl.BaseUser
import org.apache.ftpserver.usermanager.impl.WritePermission

class FTPServer{
    private var ftpServer: FtpServer? = null

    fun startServer(port: Int, username: String, mPassword: String, homeDirectory: String): Boolean {
        stopServer()
        return try {
            val serverFactory = FtpServerFactory()
            val listenerFactory = ListenerFactory()
            listenerFactory.port = port

            serverFactory.addListener("default", listenerFactory.createListener())

            val userManagerFactory = PropertiesUserManagerFactory()
            val userManager = userManagerFactory.createUserManager() as UserManager

            val user = BaseUser().apply {
                name = username
                password = mPassword
                this.homeDirectory = homeDirectory
                authorities = listOf<Authority>(WritePermission())
            }

            userManager.save(user)
            serverFactory.userManager = userManager

            ftpServer = serverFactory.createServer()
            ftpServer?.start()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun stopServer() {
        try {
            ftpServer?.stop()
        } catch (_: Exception) {
        }
        ftpServer = null
    }
}