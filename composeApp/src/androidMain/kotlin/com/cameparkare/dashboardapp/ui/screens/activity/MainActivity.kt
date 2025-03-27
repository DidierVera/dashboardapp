package com.cameparkare.dashboardapp.ui.screens.activity

import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.AndroidApiServer
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.WebAppServer
import com.cameparkare.dashboardapp.ui.screens.main.MainScreen
import com.cameparkare.dashboardapp.ui.theme.DashboardAppTheme
import com.cameparkare.dashboardapp.ui.utils.ConfigUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainActivityViewModel by viewModel()
    private var webAppServer:  WebAppServer? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onStart() {
        super.onStart()
        ConfigUI.hideSystemUI(this)
        startFTPServer()
        startAndroidApiServer()
        setContent()
    }

    private fun startAndroidApiServer() {
        mainViewModel.initAndroidServer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun startFTPServer() {
        coroutineScope.launch {
            // Start the first FTP server (port 8888, app data directory)
            val appDataDir = getExternalFilesDir(null)!!.absolutePath
            mainViewModel.startAppFtpServer(appDataDir)
            // Start the second FTP server (port 2121, root directory)
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
                    MainScreen()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWebServer()
        copyWebAppFiles()
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
                webAppServer?.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
        val ipAddress = ConfigUI.getEthernetIpAddress() // Utiliza una función para obtener la IP del dispositivo
        val ipByWifi = ConfigUI.getWifiIpAddress()
        ipAddress?.let {
            Toast.makeText(this, "Servidor web: http://$it:8080", Toast.LENGTH_LONG).show()
        }
        ipByWifi?.let {
            Toast.makeText(this, "Servidor web: http://$it:8080", Toast.LENGTH_LONG).show()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MainScreen()
}