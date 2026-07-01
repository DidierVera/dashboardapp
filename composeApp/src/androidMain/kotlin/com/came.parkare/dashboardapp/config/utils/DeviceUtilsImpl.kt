package com.came.parkare.dashboardapp.config.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.pm.PackageInfoCompat
import com.came.parkare.dashboardapp.config.dataclasses.AppVersion

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