package com.cameparkare.dashboardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.WebAppServer
import com.cameparkare.dashboardapp.ui.screens.main.MainScreen
import com.cameparkare.dashboardapp.ui.theme.DashboardAppTheme
import com.cameparkare.dashboardapp.ui.utils.ConfigUI
import java.io.File
import java.io.IOException

class MainActivity : ComponentActivity() {
    private var webAppServer:  WebAppServer? = null

    override fun onStart() {
        super.onStart()
        ConfigUI.hideSystemUI(this)
        setContent()
    }

    private fun setContent() {
        setContent {
            DashboardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        copyWebAppFiles()
        initWebServer()
    }

    private fun copyWebAppFiles() {
        val assetManager = assets
        val outputDir = filesDir

        assetManager.list("webapp")?.forEach { fileName ->
            val inputStream = assetManager.open("webapp/$fileName")
            val outputFile = File(outputDir, fileName)
            inputStream.use { input ->
                outputFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

    private fun initWebServer() {
        webAppServer = WebAppServer(this, 8080)
        Thread {
            try {
                webAppServer?.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
        val ipAddress = ConfigUI.getEthernetIpAddress()
        println("Servidor web disponible en: http://$ipAddress:8080")
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MainScreen()
}