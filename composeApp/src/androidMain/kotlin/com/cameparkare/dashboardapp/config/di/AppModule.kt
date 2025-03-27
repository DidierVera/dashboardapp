package com.cameparkare.dashboardapp.config.di

import androidx.room.Room
import com.cameparkare.dashboardapp.config.database.AppDatabase
import com.cameparkare.dashboardapp.config.database.migrations.Migration_2_3
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.AppLoggerImpl
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesWrapper
import com.cameparkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.cameparkare.dashboardapp.domain.repositories.external.FtpServerFileRepository
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.cameparkare.dashboardapp.domain.usecases.ConnectionConfig
import com.cameparkare.dashboardapp.domain.usecases.FtpServerConfiguration
import com.cameparkare.dashboardapp.domain.usecases.GetScreenByDispatcher
import com.cameparkare.dashboardapp.domain.usecases.InitConfiguration
import com.cameparkare.dashboardapp.domain.usecases.StartSocketConnection
import com.cameparkare.dashboardapp.getPlatform
import com.cameparkare.dashboardapp.infrastructure.repositories.external.ConfigFileRepositoryImpl
import com.cameparkare.dashboardapp.infrastructure.repositories.external.FtpServerFileImpl
import com.cameparkare.dashboardapp.infrastructure.repositories.local.DashboardElementRepositoryImpl
import com.cameparkare.dashboardapp.infrastructure.repositories.remote.TerminalConnectionImpl
import com.cameparkare.dashboardapp.infrastructure.source.external.ConfigFileDao
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.AndroidApiServer
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiServerRepository
import com.cameparkare.dashboardapp.infrastructure.source.remote.apiserver.ApiServerRepositoryImpl
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.MockService
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.SignalRService
import com.cameparkare.dashboardapp.infrastructure.source.remote.services.TerminalSocketService
import com.cameparkare.dashboardapp.ui.screens.activity.MainActivityViewModel
import com.cameparkare.dashboardapp.ui.screens.main.MainViewModel
import com.cameparkare.dashboardapp.ui.utils.FTPServer
import com.cameparkare.dashboardapp.ui.utils.FilesUtils
import com.cameparkare.dashboardapp.ui.utils.FilesUtilsImpl
import com.cameparkare.dashboardapp.ui.utils.ServerConnectionImpl
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtilsImpl
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
    factory { AndroidApiServer(get()) }
}

val viewModelModule = module {
    viewModelOf(::MainActivityViewModel)
    viewModelOf(::MainViewModel)
}

val useCasesModule = module {
    factory { ConnectionConfig(get(), get()) }
    factory { InitConfiguration(get(), get()) }
    factory { StartSocketConnection(get(), get()) }
    factory { FtpServerConfiguration(get(), get()) }
    factory { GetScreenByDispatcher(get(), get()) }
}
val repositoryModule = module {
    singleOf(::TerminalConnectionImpl) { bind<TerminalConnectionRepository>() }
    singleOf(::FtpServerFileImpl) { bind<FtpServerFileRepository>() }
    singleOf(::ConfigFileRepositoryImpl) { bind<ConfigFileRepository>() }
    singleOf(::DashboardElementRepositoryImpl) { bind<DashboardElementRepository>() }
    singleOf(::ApiServerRepositoryImpl) { bind<ApiServerRepository>() }
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
            .addMigrations(Migration_2_3)
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
}