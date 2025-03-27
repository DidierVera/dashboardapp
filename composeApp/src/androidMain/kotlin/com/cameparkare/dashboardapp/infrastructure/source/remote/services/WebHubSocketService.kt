package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class WebHubSocketService(private val port: Int = 9022) {
    private var isRunning = true
    private val serverScope = CoroutineScope(Dispatchers.IO)

    fun start() {
        serverScope.launch {
            try {
                val serverSocket = ServerSocket(port)
                println("Server started on port $port")

                while (isRunning) {
                    val clientSocket = serverSocket.accept()
                    println("Client connected: ${clientSocket.inetAddress}")

                    handleClient(clientSocket)
                }

                serverSocket.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleClient(clientSocket: Socket) {
        serverScope.launch {
            try {
                val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                val writer = PrintWriter(clientSocket.getOutputStream(), true)

                var message: String?
                while (reader.readLine().also { message = it } != null) {
                    println("Received: $message")
                    writer.println("Echo: $message")
                }

                clientSocket.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun stop() {
        isRunning = false
        serverScope.cancel()
    }
}