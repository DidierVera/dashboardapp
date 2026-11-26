package com.came.parkare.dashboardapp.config.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.pm.PackageInfoCompat
import com.came.parkare.dashboardapp.config.dataclasses.AppVersion
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.device.DisplaySizeDto
import kotlin.math.roundToInt

class DeviceUtilsImpl(
    private val context: Context
): DeviceUtils {

    override fun getAppVersion(): AppVersion? {
        return try {
            val packageManager = context.packageManager
            val packageName = context.packageName
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
            } else {
                packageManager.getPackageInfo(packageName, 0)
            }
            AppVersion(
                versionName = packageInfo.versionName,
                versionNumber = PackageInfoCompat.getLongVersionCode(packageInfo),
            )
        } catch (e: Exception) {
            null
        }
    }

    override fun getDisplaySize(): DisplaySizeDto? {
        return try {
            // Real size of the display (the app runs full screen, without system bars)
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getRealMetrics(metrics)
            DisplaySizeDto(
                widthDp = (metrics.widthPixels / metrics.density).roundToInt(),
                heightDp = (metrics.heightPixels / metrics.density).roundToInt(),
                density = metrics.density
            )
        } catch (e: Exception) {
            null
        }
    }

    override fun getDeviceSerialNumber(): String? {
        var serialNumber: String? = null

        // For API level  29 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Requires READ_PRIVILEGED_PHONE_STATE permission or being the device owner
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                serialNumber = try {
                    Build.getSerial()
                }catch(e: Exception){
                    Log.i("GET_SERIA_NUMBER_TAG", e.message.toString())
                    "offlineLoginCode" //default when the app not have allowed permissions to get serial number
                }
            }
        }
        // For API level  26 to  28
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            serialNumber = Build.getSerial()
        }
        // For API levels below O
        else {
            serialNumber = Build.SERIAL
        }

        Log.i("SERIAL_NUMBER: ", serialNumber ?: "Is Null")
        return if (serialNumber == Build.UNKNOWN || serialNumber.isNullOrEmpty()) {
            null
        } else {
            serialNumber
        }
    }

}