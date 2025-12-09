package com.came.parkare.dashboardapp.ui.screens.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.WebAppServer
import com.came.parkare.dashboardapp.ui.screens.main.MainScreen
import com.came.parkare.dashboardapp.ui.theme.DashboardAppTheme
import com.came.parkare.dashboardapp.ui.utils.ConfigUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext
import java.io.File
import java.io.IOException

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainActivityViewModel by viewModel()
    private var webAppServer:  WebAppServer? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onStart() {
        super.onStart()
        ConfigUI.hideSystemUI(this)
        checkPermission()
        startServices()
    }

    private fun checkPermission() {
        if (!Settings.System.canWrite(this)) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = ("package:$packageName").toUri()
                startActivity(intent)
            }catch (e: Exception){
                println(e.stackTrace)
            }
        }
    }

    private fun startAndroidApiServer() {
        mainViewModel.initAndroidServer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
    private fun startServices() {
        // Start services in sequence with delays
        Handler(Looper.getMainLooper()).postDelayed({
            startFTPServer()
        }, 500)

        Handler(Looper.getMainLooper()).postDelayed({
            startAndroidApiServer()
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            initWebServer()
            copyWebAppFiles()
        }, 1500)

        setContent()
    }

    private fun startFTPServer() {
        coroutineScope.launch {
            val appDataDir = getExternalFilesDir(null)!!.absolutePath
            mainViewModel.startAppFtpServer(appDataDir)

            val rootDir = Environment.getExternalStorageDirectory().absolutePath
            mainViewModel.startDeviceFtpServer(rootDir)
        }
    }

    private fun setContent() {
        setContent {
            DashboardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {

                    KoinContext() {
                        MainScreen()
                    }
                }
            }
        }
    }

    private fun copyWebAppFiles() {
        val assetManager = assets
        val outputDir = filesDir

        assetManager.list("webapp")?.forEach { fileName ->
            if(File("webapp/$fileName").isFile){
                val inputStream = assetManager.open("webapp/$fileName")
                val outputFile = File(outputDir, fileName)
                inputStream.use { input ->
                    outputFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }
        }
    }

    private fun initWebServer() {
        webAppServer = WebAppServer(this, 8080)
        Thread {
            try {
                webAppServer?.stop()
                webAppServer?.start()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }.start()
        val ipAddress = ConfigUI.getEthernetIpAddress() // Utiliza una función para obtener la IP del dispositivo
        val ipByWifi = ConfigUI.getWifiIpAddress()
        ipAddress?.let {
            Toast.makeText(this, "Configurador web: http://$it:8080", Toast.LENGTH_SHORT).show()
        }
        ipByWifi?.let {
            Toast.makeText(this, "Configurador web: http://$it:8080", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        webAppServer?.stop()
        mainViewModel.stopServers()
        super.onDestroy()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MainScreen()
}