package com.came.parkare.dashboardapp

import android.app.Application
import android.content.Intent
import android.util.Log
import com.came.parkare.dashboardapp.config.di.initKoin
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.ui.screens.splash.SplashActivity
import com.google.firebase.BuildConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
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
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = !BuildConfig.DEBUG
        setupCrashHandler()
    }


    private fun setupCrashHandler() {
        val appLogger = getKoin().get<AppLogger>()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            appLogger.trackLog("APP CRASHED", throwable.message)
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