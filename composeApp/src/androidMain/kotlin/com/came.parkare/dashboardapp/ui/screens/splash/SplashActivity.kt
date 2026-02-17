package com.came.parkare.dashboardapp.ui.screens.splash

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.came.parkare.dashboardapp.R
import com.came.parkare.dashboardapp.ui.screens.activity.MainActivity
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import androidx.core.net.toUri
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import org.koin.androidx.compose.koinViewModel

class SplashActivity : ComponentActivity() {

    // ✅ Launcher para el permiso WRITE_SETTINGS (brillo)
    private val brightnessLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // Al regresar de la pantalla de WRITE_SETTINGS, solicita los demás permisos
        requestRuntimePermissions()
    }

    // ✅ Launcher para permisos en runtime (reemplaza onRequestPermissionsResult)
    private val permissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            redirectToMain()
        } else {
            showToast("Some permissions have not been allowed")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestRuntimePermissions() // Reintenta en Android 13+
            } else {
                redirectToMain()
            }
        }
    }

    // ✅ setContent en onCreate, no en onStart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext() {
                SplashScreen()
            }
        }
    }

    @Preview
    @Composable
    private fun SplashScreen() {
        var progress by remember { mutableStateOf(0.1f) }

        val viewModel: SplashViewModel = koinViewModel()
        val version by viewModel.appVersion.collectAsState()

        val animatedProgress = animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
            label = "loading"
        ).value

        // ✅ Sin scope.launch redundante dentro de LaunchedEffect
        LaunchedEffect(key1 = true) {
            for (i in 1..100) {
                progress = i.toFloat() / 100
                delay(10)
            }
            Log.i("LOG_TAG", "Progress bar finished, requesting permissions")
            requestBrightnessPermission()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.came_logotipo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.height(55.dp))
            LinearProgressIndicator(
                modifier = Modifier.width(300.dp),
                color = CameBlueColor,
                progress = animatedProgress
            )
            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = version,
                color = WhiteColor,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

    // ✅ Paso 1: Solicita permiso de brillo y espera respuesta antes de continuar
    private fun requestBrightnessPermission() {
        if (!Settings.System.canWrite(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
                data = "package:${packageName}".toUri()
            }
            brightnessLauncher.launch(intent)
        } else {
            // Ya tiene el permiso, continúa con los demás
            requestRuntimePermissions()
        }
    }

    // ✅ Paso 2: Solicita los permisos en runtime
    private fun requestRuntimePermissions() {
        val permissionsToRequest: MutableList<String> = mutableListOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_IMAGES
        )
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        val permissionsDenied = permissionsToRequest.filter { perm ->
            ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED
                .also { Log.i("REQUEST_PERMI", "Checking: $perm") }
        }

        if (permissionsDenied.isNotEmpty()) {
            Log.i("REQUEST_PERMI", "Requesting ${permissionsDenied.size} permissions")
            permissionsLauncher.launch(permissionsDenied.toTypedArray())
        } else {
            Log.i("REQUEST_PERMI", "All permissions already granted")
            showToast("Permissions already granted")
            redirectToMain()
        }
    }

    private fun redirectToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}