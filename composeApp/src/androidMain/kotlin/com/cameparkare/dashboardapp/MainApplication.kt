package com.cameparkare.dashboardapp

import android.app.Application
//import com.cameparkare.dashboardapp.config.di.initKoin
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        initKoin {
//            androidContext(this@MainApplication)
//            androidLogger()
//        }
    }
}