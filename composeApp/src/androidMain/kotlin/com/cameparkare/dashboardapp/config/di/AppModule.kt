package com.cameparkare.dashboardapp.config.di

import com.cameparkare.dashboardapp.domain.repositories.remote.TerminalConnectionRepository
import com.cameparkare.dashboardapp.getPlatform
import com.cameparkare.dashboardapp.infrastructure.repositories.remote.TerminalConnectionImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TerminalConnectionImpl) { bind<TerminalConnectionRepository>() }
//    viewModelOf(::UserViewModel)
    factory { getPlatform() }
}