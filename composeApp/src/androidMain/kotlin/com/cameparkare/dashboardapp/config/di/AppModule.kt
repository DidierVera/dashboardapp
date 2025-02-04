package com.cameparkare.dashboardapp.config.di

import com.cameparkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.cameparkare.dashboardapp.getPlatform
import com.cameparkare.dashboardapp.infrastructure.repositories.remote.TerminalConnectionImpl
import com.cameparkare.dashboardapp.ui.utils.FilesUtils
import com.cameparkare.dashboardapp.ui.utils.FilesUtilsImpl
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtilsImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TerminalConnectionImpl) { bind<TerminalConnectionRepository>() }
    singleOf(::UiUtilsImpl) { bind<UiUtils>() }
    singleOf(::FilesUtilsImpl) { bind<FilesUtils>() }
//    viewModelOf(::UserViewModel)
    factory { getPlatform() }
}