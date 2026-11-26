package com.came.parkare.dashboardapp.ui.screens.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
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
import com.came.parkare.dashboardapp.config.constants.DefaultPorts
import com.came.parkare.dashboardapp.config.utils.ServerPorts
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.WebAppServer
import com.came.parkare.dashboardapp.ui.screens.main.MainScreen
import com.came.parkare.dashboardapp.ui.theme.DashboardAppTheme
import com.came.parkare.dashboardapp.ui.utils.ConfigUI
import com.came.parkare.dashboardapp.ui.utils.startWithFallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        startServices()
    }

    private fun startServices() {
        coroutineScope.launch {
            // The ports come from ftp_connection.json and connection_config.json, read before
            // any server starts so a change in those files is applied on this start
            val resolvedPorts = mainViewModel.loadServerPorts()
            setContent()
            resolvedPorts.warnings.forEach { showMessage(it) }
            val ports = resolvedPorts.ports

            // Start services in sequence with delays
            delay(500)
            startFTPServer(ports)

            delay(500)
            val apiPort = startAndroidApiServer(ports.api)

            delay(500)
            // If the API did not start, the web still points to the configured port
            initWebServer(ports.web, apiPort ?: ports.api)
            copyWebAppFiles()
        }
    }

    private suspend fun startFTPServer(ports: ServerPorts) {
        val appDataDir = getExternalFilesDir(null)!!.absolutePath
        mainViewModel.startAppFtpServer(appDataDir, ports.ftpApp, ::showMessage)

        val rootDir = Environment.getExternalStorageDirectory().absolutePath
        mainViewModel.startDeviceFtpServer(rootDir, ports.ftpDevice, ::showMessage)
    }

    private suspend fun startAndroidApiServer(port: Int): Int? {
        val apiPort = mainViewModel.startAndroidApiServer(port, ::showMessage) ?: return null
        showDeviceUrls("API iniciado en", apiPort)
        return apiPort
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

    private suspend fun initWebServer(port: Int, apiPort: Int) {
        val webPort = withContext(Dispatchers.IO) {
            // Before, a new instance was created on each onStart and the previous one kept the port
            webAppServer?.stop()
            webAppServer = null

            startWithFallback("Web", port, DefaultPorts.WEB, { message ->
                mainViewModel.logServerMessage(message)
                runOnUiThread { showMessage(message) }
            }) { p ->
                val server = WebAppServer(this@MainActivity, p, apiPort)
                try {
                    server.start()
                    webAppServer = server
                    true
                } catch (e: IOException) {
                    e.printStackTrace()
                    false
                }
            }
        } ?: return

        showDeviceUrls("Configurador web", webPort, Toast.LENGTH_SHORT)
    }

    private fun showDeviceUrls(label: String, port: Int, duration: Int = Toast.LENGTH_LONG) {
        val ipAddress = ConfigUI.getEthernetIpAddress() // Utiliza una función para obtener la IP del dispositivo
        val ipByWifi = ConfigUI.getWifiIpAddress()
        ipAddress?.let {
            Toast.makeText(this, "$label: http://$it:$port", duration).show()
        }
        ipByWifi?.let {
            Toast.makeText(this, "$label: http://$it:$port", duration).show()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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