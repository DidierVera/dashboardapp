package com.cameparkare.dashboardapp.config.di

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.AppLoggerImpl
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesWrapper
import com.cameparkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.cameparkare.dashboardapp.domain.usecases.GetCardClassTranslations
import com.cameparkare.dashboardapp.domain.usecases.GetCardClassTranslationsImpl
import com.cameparkare.dashboardapp.getPlatform
import com.cameparkare.dashboardapp.infrastructure.repositories.remote.TerminalConnectionImpl
import com.cameparkare.dashboardapp.ui.screens.main.MainViewModel
import com.cameparkare.dashboardapp.ui.utils.FilesUtils
import com.cameparkare.dashboardapp.ui.utils.FilesUtilsImpl
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtilsImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::UiUtilsImpl) { bind<UiUtils>() }
    singleOf(::FilesUtilsImpl) { bind<FilesUtils>() }
    singleOf(::SharedPreferencesWrapper) { bind<SharedPreferencesProvider>() }
    singleOf(::AppLoggerImpl) { bind<AppLogger>() }

    viewModelOf(::MainViewModel)
    factory { getPlatform() }
}

val useCasesModule = module {
    singleOf(::GetCardClassTranslationsImpl) { bind< GetCardClassTranslations>() }
}

val repositoryModule = module {
    singleOf(::TerminalConnectionImpl) { bind<TerminalConnectionRepository>() }
}