package com.cameparkare.dashboardapp.config.di

import com.cameparkare.dashboardapp.ui.navigation.Navigator
import com.cameparkare.dashboardapp.ui.screens.settings.viewmodels.SettingViewModel
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtilsImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

// wasmJsMain/src/.../WasmKoinModule.kt
val wasmAppModule = module {
    singleOf(::UiUtilsImpl) { bind<UiUtils>() }

    //viewModels
    viewModelOf(::SettingViewModel)

    single { Navigator() }
}