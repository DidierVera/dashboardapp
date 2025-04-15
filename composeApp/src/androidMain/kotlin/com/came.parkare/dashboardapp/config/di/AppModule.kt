package com.came.parkare.dashboardapp.config.di

import androidx.room.Room
import com.came.parkare.dashboardapp.config.database.AppDatabase
import com.came.parkare.dashboardapp.config.database.migrations.Migration_2_3
import com.came.parkare.dashboardapp.config.database.migrations.Migration_3_4
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.AppLoggerImpl
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesWrapper
import com.came.parkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.came.parkare.dashboardapp.domain.repositories.external.FtpServerFileRepository
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardDevicesRepository
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.domain.repositories.remote.ApiServerRepository
import com.came.parkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.came.parkare.dashboardapp.domain.usecases.ConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.FtpServerConfiguration
import com.came.parkare.dashboardapp.domain.usecases.GetScreenByDispatcher
import com.came.parkare.dashboardapp.domain.usecases.GetScreenConfigurations
import com.came.parkare.dashboardapp.domain.usecases.InitConfiguration
import com.came.parkare.dashboardapp.domain.usecases.StartSocketConnection
import com.came.parkare.dashboardapp.getPlatform
import com.came.parkare.dashboardapp.infrastructure.repositories.external.ConfigFileRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.repositories.external.FtpServerFileImpl
import com.came.parkare.dashboardapp.infrastructure.repositories.local.DashboardDeviceRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.repositories.local.DashboardElementRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.repositories.remote.TerminalConnectionImpl
import com.came.parkare.dashboardapp.infrastructure.source.external.ConfigFileDao
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.AndroidApiServer
import com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver.ApiServerRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.MockService
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.SignalRService
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.TerminalSocketService
import com.came.parkare.dashboardapp.ui.screens.activity.MainActivityViewModel
import com.came.parkare.dashboardapp.ui.screens.main.MainViewModel
import com.came.parkare.dashboardapp.ui.utils.FTPServer
import com.came.parkare.dashboardapp.ui.utils.FilesUtils
import com.came.parkare.dashboardapp.ui.utils.FilesUtilsImpl
import com.came.parkare.dashboardapp.ui.utils.ServerConnectionImpl
import com.came.parkare.dashboardapp.ui.utils.UiUtils
import com.came.parkare.dashboardapp.ui.utils.UiUtilsImpl
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val utilsModule = module {
    singleOf(::FilesUtilsImpl) { bind<FilesUtils>() }
    singleOf(::UiUtilsImpl) { bind<UiUtils>() }
    singleOf(::SharedPreferencesWrapper) { bind<SharedPreferencesProvider>() }
    singleOf(::AppLoggerImpl) { bind<AppLogger>() }
    singleOf(::ServerConnectionImpl) { bind<IServerConnection>() }
    factory { getPlatform() }
    factory { FTPServer() }
    factory { AndroidApiServer(get(),get(),get()) }
    single<FirebaseAnalytics> {
        FirebaseAnalytics.getInstance(get())
    }
}

val viewModelModule = module {
    viewModelOf(::MainActivityViewModel)
    viewModelOf(::MainViewModel)
}

val useCasesModule = module {
    factory { ConnectionConfig(get(), get()) }
    factory { InitConfiguration(get(), get(), get(), get()) }
    factory { StartSocketConnection(get(), get()) }
    factory { FtpServerConfiguration(get(), get()) }
    factory { GetScreenByDispatcher(get(), get()) }
    factory { GetScreenConfigurations(get(), get()) }
}
val repositoryModule = module {
    singleOf(::TerminalConnectionImpl) { bind<TerminalConnectionRepository>() }
    singleOf(::FtpServerFileImpl) { bind<FtpServerFileRepository>() }
    singleOf(::ConfigFileRepositoryImpl) { bind<ConfigFileRepository>() }
    singleOf(::DashboardElementRepositoryImpl) { bind<DashboardElementRepository>() }
    singleOf(::ApiServerRepositoryImpl) { bind<ApiServerRepository>() }
    singleOf(::DashboardDeviceRepositoryImpl) { bind<DashboardDevicesRepository>() }
}

val servicesModule = module {
    factory { TerminalSocketService(get(), get(), get()) }
    factory { MockService(get(), get()) }
    factory { SignalRService(get(), get(), get()) }
}

val daoModule = module {
    factory { ConfigFileDao(get(), get()) }
}

val databaseModules = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "dashboard_db"
        )
            .fallbackToDestructiveMigration()
            .addMigrations(Migration_2_3, Migration_3_4)
            .build()
    }
    single {
        val database = get<AppDatabase>()
        database.screenDao()
    }
    single {
        val database = get<AppDatabase>()
        database.imagesDao()
    }
    single {
        val database = get<AppDatabase>()
        database.dashboardDeviceDao()
    }
}