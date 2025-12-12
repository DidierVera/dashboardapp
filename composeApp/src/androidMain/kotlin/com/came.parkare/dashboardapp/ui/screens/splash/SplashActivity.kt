package com.came.parkare.dashboardapp.ui.screens.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


class SplashActivity: ComponentActivity() {

    override fun onStart() {
        super.onStart()
        setContent{
            KoinContext() {
                SplashScreen()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @Preview
    @Composable
    private fun SplashScreen() {
        val scope = rememberCoroutineScope() // Create a coroutine scope
        var progress by remember { mutableStateOf(0.1f) }

        val viewModel: SplashViewModel = koinViewModel()
        val version by viewModel.appVersion.collectAsState()

        val animatedProgress = animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = "loading"
        ).value

        LaunchedEffect(key1 = true){
            scope.launch {
                for (i in 1..100) {
                    progress = i.toFloat() / 100
                    delay(10)
                }
                Log.i("LOG_TAG", "Request permissions end progress bar")
                requestStoragePermissions()
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(BlackColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.came_logotipo),
                contentDescription = null, contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(300.dp))
            Spacer(modifier = Modifier.height(55.dp))
            LinearProgressIndicator(modifier = Modifier.width(300.dp)
                ,color = CameBlueColor
                , progress = animatedProgress)
            Spacer(modifier = Modifier.height(55.dp))
            Text(text = version, color = WhiteColor, style = MaterialTheme.typography.labelMedium)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i("LOG_TAG", "Permissions result $requestCode")
        for (permission in permissions) {
            Log.i("LOG_TAG", "Permissions result $permission")
        }

        when (requestCode) {
            10001 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults.all { it == PackageManager.PERMISSION_GRANTED } )) {
                    redirectToMain()
                } else {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU){
                        requestStoragePermissions()
                        showToast("Some Permissions have no been allowed")
                    }else{
                        redirectToMain()
                    }
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
        showToast("Some permissions are not allowed")
    }

    private fun redirectToMain() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        this@SplashActivity.finish()
    }

    private fun requestStoragePermissions() {
        Log.i("REQUEST_PERMI", "All Permissions")
        val permissionsToRequest: MutableList<String> = mutableListOf(
            "android.permission.READ_PRIVILEGED_PHONE_STATE",
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_IMAGES
        )
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        checkPermission(permissionsToRequest.toTypedArray() , 10001)
    }

    // Function to check and request permission.
    private fun checkPermission(permissions: Array<String>, requestCode: Int) {
        val permissionsDenied : MutableList<String> = mutableListOf()

        permissions.forEach { perm ->
            if(ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED)
                permissionsDenied.add(perm)
        }
        if(permissionsDenied.any()) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, permissionsDenied.toTypedArray(), requestCode)
        } else {
            showToast("Permission already granted")
            redirectToMain()
        }
    }
}