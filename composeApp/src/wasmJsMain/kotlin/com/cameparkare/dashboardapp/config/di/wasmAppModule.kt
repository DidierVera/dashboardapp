package com.cameparkare.dashboardapp.config.di

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.config.utils.WasmAppLoggerImpl
import com.cameparkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.cameparkare.dashboardapp.config.utils.WebStoragePreferences
import com.cameparkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.cameparkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.cameparkare.dashboardapp.domain.usecases.GetScreensConfig
import com.cameparkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.cameparkare.dashboardapp.infrastructure.repositories.device.DeviceRepositoryImpl
import com.cameparkare.dashboardapp.infrastructure.repositories.screen.ScreenRepositoryImpl
import com.cameparkare.dashboardapp.infrastructure.source.services.base.HttpClient
import com.cameparkare.dashboardapp.ui.navigation.Navigator
import com.cameparkare.dashboardapp.ui.screens.settings.components.viewmodels.FilePickerDialogViewModel
import com.cameparkare.dashboardapp.ui.screens.settings.importfile.ImportViewModel
import com.cameparkare.dashboardapp.ui.screens.settings.SettingViewModel
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtilsImpl
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

    //viewModels
    viewModelOf(::SettingViewModel)
    viewModelOf(::FilePickerDialogViewModel)
    viewModelOf(::ImportViewModel)

    //repositories
    singleOf(::DeviceRepositoryImpl) { bind<DeviceRepository>() }
    singleOf(::ScreenRepositoryImpl) { bind<ScreenRepository>() }

    //useCases
    single { GetScreensConfig(get()) }
    single { SaveScreenConfig(get()) }


    single { Navigator() }
    single { HttpClient() }
}


fun provideStorage(): Storage {
    return window.localStorage
}