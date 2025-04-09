package com.came.parkare.dashboardapp.config.di

import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WasmAppLoggerImpl
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WebStoragePreferences
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.came.parkare.dashboardapp.infrastructure.repositories.device.DeviceRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.repositories.screen.ScreenRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.source.services.base.HttpClient
import com.came.parkare.dashboardapp.ui.components.loading.LoadingHandler
import com.came.parkare.dashboardapp.ui.components.loading.LoadingHandlerImpl
import com.came.parkare.dashboardapp.ui.components.viewmodels.AppLoadingViewModel
import com.came.parkare.dashboardapp.ui.navigation.Navigator
import com.came.parkare.dashboardapp.ui.screens.home.HomeViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.components.viewmodels.FilePickerDialogViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.importfile.ImportViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.SettingViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.connection.ConnectionViewModel
import com.came.parkare.dashboardapp.ui.utils.UiUtils
import com.came.parkare.dashboardapp.ui.utils.UiUtilsImpl
import kotlinx.browser.window
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.w3c.dom.Storage

// wasmJsMain/src/.../WasmKoinModule.kt
val wasmAppModule = module {
    //utils
    singleOf(::UiUtilsImpl) { bind<UiUtils>() }
    singleOf(::WasmAppLoggerImpl) { bind<AppLogger>() }
    single { provideStorage() }
    singleOf(::WebStoragePreferences) { bind<WasmSharedPreferencesProvider>() }
    singleOf(::WebStoragePreferences) { bind<SharedPreferencesProvider>() }
    singleOf(::LoadingHandlerImpl) { bind<LoadingHandler>() }

    //viewModels
    viewModelOf(::SettingViewModel)
    viewModelOf(::FilePickerDialogViewModel)
    viewModelOf(::ImportViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ConnectionViewModel)
    viewModelOf(::AppLoadingViewModel)

    //repositories
    singleOf(::DeviceRepositoryImpl) { bind<DeviceRepository>() }
    singleOf(::ScreenRepositoryImpl) { bind<ScreenRepository>() }

    //useCases
    single { GetScreensConfig(get()) }
    single { SaveScreenConfig(get()) }
    single { SaveConnectionConfig(get(), get()) }
    single { GetConnectionConfig(get(), get()) }


    single { Navigator() }
    single { HttpClient() }
}


fun provideStorage(): Storage {
    return window.localStorage
}