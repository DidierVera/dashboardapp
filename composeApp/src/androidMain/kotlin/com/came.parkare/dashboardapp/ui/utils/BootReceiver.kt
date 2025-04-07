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
    }
}