package com.came.parkare.dashboardapp.config.di

import com.came.parkare.dashboardapp.MainApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config : KoinAppDeclaration? = null){
    startKoin {
        printLogger()
        includes(config)
        modules(
            databaseModules,
            utilsModule,
            useCasesModule,
            daoModule,
            servicesModule,
            viewModelModule,
            repositoryModule)
    }
}