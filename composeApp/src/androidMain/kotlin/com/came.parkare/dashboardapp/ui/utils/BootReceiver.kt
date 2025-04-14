package com.came.parkare.dashboardapp.ui.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.ui.screens.splash.SplashActivity
import org.koin.mp.KoinPlatform.getKoin

class BootReceiver(
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val appLogger = getKoin().get<AppLogger>()
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            appLogger.trackLog("BootReceiver", "Device booted, checking launcher status...")
            val mainIntent = Intent(context, SplashActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(mainIntent)
        }
        if (intent.action == Intent.ACTION_USER_PRESENT) {
            appLogger.trackLog("MyBroadcastReceiver", "User is present, launching app")
            val launchIntent = Intent(context, SplashActivity::class.java)
            launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(launchIntent)
        }
    }
}