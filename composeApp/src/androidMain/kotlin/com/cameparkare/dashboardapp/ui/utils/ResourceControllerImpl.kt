package com.cameparkare.dashboardapp.ui.utils

import android.content.Context
import android.net.wifi.WifiManager
import com.cameparkare.dashboardapp.ui.interfaces.ResourceController
import javax.inject.Inject

class ResourceControllerImpl @Inject constructor(private val context: Context
) : ResourceController {
    override fun getString(resourceId: Int): String {
        return context.applicationContext.getString(resourceId)
    }
    override fun getDeviceIpAddress(): String {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress = wifiManager.connectionInfo.ipAddress
        return String.format("%d.%d.%d.%d", ipAddress and 0xff, ipAddress shr 8 and 0xff, ipAddress shr 16 and 0xff, ipAddress shr 24 and 0xff)
    }
}