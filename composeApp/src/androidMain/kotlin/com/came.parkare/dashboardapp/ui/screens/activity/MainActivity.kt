package com.came.parkare.dashboardapp.ui.screens.activity

import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConfigUI.hideSystemUI(this)
        startServices()
    }

    private fun startAndroidApiServer() {
        mainViewModel.initAndroidServer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
    private fun startServices() {
        // Start services in sequence with delays
        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("StartSERVICES", "FTP Service Starting")
            startFTPServer()
        }, 500)

        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("StartSERVICES", "Android api server Starting")
            startAndroidApiServer()
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("StartSERVICES", "Web Server Starting")
            initWebServer()
            copyWebAppFiles()
        }, 1500)

        setContent()
    }

    private fun startFTPServer() {
        Log.i("START_FTP", "startFTPServer() called")
        coroutineScope.launch {
            try {
                val appDataDir = getExternalFilesDir(null)!!.absolutePath
                Log.i("START_FTP", "App data dir: $appDataDir")
                mainViewModel.startAppFtpServer(appDataDir)

                val rootDir = Environment.getExternalStorageDirectory().absolutePath
                Log.i("START_FTP", "Root dir: $rootDir")
                mainViewModel.startDeviceFtpServer(rootDir)
                Log.i("START_FTP", "FTP servers started successfully")
            } catch (e: Exception) {
                Log.e("START_FTP", "FTP server error", e)
            }
        }
    }

    private fun setContent() {
        setContent {
            DashboardAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    KoinContext {
                        AppRoot()
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
        Log.i("START_WEB", "initWebServer() called")
        webAppServer = WebAppServer(this, 8080)
        Thread {
            try {
                Log.i("START_WEB", "Starting web server...")
                webAppServer?.stop()
                webAppServer?.start()
                Log.i("START_WEB", "Web server started successfully")
            } catch (e: IOException) {
                Log.e("START_WEB", "First attempt failed: ${e.message}")
                e.printStackTrace()
                // Retry after delay to let TIME_WAIT expire
                Thread.sleep(3000)
                try {
                    webAppServer?.stop()
                    webAppServer?.start()
                    Log.i("START_WEB", "Web server started on retry")
                } catch (e2: Exception) {
                    Log.e("START_WEB", "Retry failed: ${e2.message}")
                    e2.printStackTrace()
                }
            } catch (e: Exception){
                Log.e("START_WEB", "First attempt failed: ${e.message}")
                e.printStackTrace()
                Thread.sleep(3000)
                try {
                    webAppServer?.stop()
                    webAppServer?.start()
                    Log.i("START_WEB", "Web server started on retry")
                } catch (e2: Exception) {
                    Log.e("START_WEB", "Retry failed: ${e2.message}")
                    e2.printStackTrace()
                }
            }
        }.start()
        val ipAddress = ConfigUI.getEthernetIpAddress()
        val ipByWifi = ConfigUI.getWifiIpAddress()
        Log.i("START_WEB", "Ethernet IP: $ipAddress, WiFi IP: $ipByWifi")
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