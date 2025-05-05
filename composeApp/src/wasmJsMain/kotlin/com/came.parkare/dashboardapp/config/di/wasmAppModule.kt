package com.came.parkare.dashboardapp.config.di

import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WasmAppLoggerImpl
import com.came.parkare.dashboardapp.config.utils.WasmSharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.WebStoragePreferences
import com.came.parkare.dashboardapp.domain.repositories.device.DeviceRepository
import com.came.parkare.dashboardapp.domain.repositories.screen.ScreenRepository
import com.came.parkare.dashboardapp.domain.usecases.DeleteDevice
import com.came.parkare.dashboardapp.domain.usecases.GetConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.GetDeviceList
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveConnectionConfig
import com.came.parkare.dashboardapp.domain.usecases.SaveNewDevice
import com.came.parkare.dashboardapp.domain.usecases.SaveScreenConfig
import com.came.parkare.dashboardapp.infrastructure.repositories.device.DeviceRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.repositories.screen.ScreenRepositoryImpl
import com.came.parkare.dashboardapp.infrastructure.source.services.base.HttpClient
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogViewModel
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandlerImpl
import com.came.parkare.dashboardapp.ui.components.loading.AppLoadingViewModel
import com.came.parkare.dashboardapp.ui.components.messages.AppToastViewModel
import com.came.parkare.dashboardapp.ui.navigation.Navigator
import com.came.parkare.dashboardapp.ui.screens.home.HomeViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.components.viewmodels.FilePickerDialogViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.importfile.ImportViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.SettingViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.connection.ConnectionViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.dashboardlist.DashboardListViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.editconfig.EditConfigViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.exportfile.ExportViewModel
import com.came.parkare.dashboardapp.ui.screens.settings.shareconfig.ShareConfigViewModel
import com.came.parkare.dashboardapp.ui.utils.ErrorValidatorImpl
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
    singleOf(::WasmUtilsHandlerImpl) { bind<WasmUtilsHandler>() }
    singleOf(::ErrorValidatorImpl) { bind<ErrorValidator>() }

    //viewModels
    viewModelOf(::SettingViewModel)
    viewModelOf(::FilePickerDialogViewModel)
    viewModelOf(::ImportViewModel)
    viewModelOf(::ExportViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ConnectionViewModel)
    viewModelOf(::AppLoadingViewModel)
    viewModelOf(::AppToastViewModel)
    viewModelOf(::DashboardListViewModel)
    viewModelOf(::AppDialogViewModel)
    viewModelOf(::EditConfigViewModel)
    viewModelOf(::ShareConfigViewModel)

    //repositories
    singleOf(::DeviceRepositoryImpl) { bind<DeviceRepository>() }
    singleOf(::ScreenRepositoryImpl) { bind<ScreenRepository>() }

    //useCases
    single { GetScreensConfig(get(), get()) }
    single { SaveScreenConfig(get(),get()) }
    single { SaveConnectionConfig(get(), get()) }
    single { GetConnectionConfig(get(), get()) }
    single { GetDeviceList(get(), get()) }
    single { SaveNewDevice(get(), get()) }
    single { DeleteDevice(get(), get()) }


    single { Navigator() }
    single { HttpClient() }
}


fun provideStorage(): Storage {
    return window.localStorage
}