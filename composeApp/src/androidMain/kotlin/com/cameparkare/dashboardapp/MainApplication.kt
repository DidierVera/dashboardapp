package com.cameparkare.dashboardapp

import android.app.Application
import android.content.Intent
import android.util.Log
import com.cameparkare.dashboardapp.config.di.initKoin
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.ui.screens.splash.SplashActivity
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.koinInject
import org.koin.mp.KoinPlatform.getKoin
import kotlin.system.exitProcess

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)
            androidLogger()
        }

        setupCrashHandler()
    }


    private fun setupCrashHandler() {
        val appLogger = getKoin().get<AppLogger>()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            appLogger.trackError(throwable as Exception)

            // Restart the launcher
            val intent = Intent(this, SplashActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            exitProcess(1)
        }
    }
}