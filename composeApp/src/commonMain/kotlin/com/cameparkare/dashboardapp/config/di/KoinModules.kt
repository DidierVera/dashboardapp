package com.cameparkare.dashboardapp.config.di

import com.cameparkare.dashboardapp.ui.interfaces.FilesUtils
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(

        )
    }
val provideDataSourceModule = module {
}

//val provideRepositoryModule = module {
//    singleOf(::).bind(FilesUtils ::class)
//}
//
//val provideUseCaseModule = module {
//    singleOf(::CreateNoteUseCase)
//    singleOf(::GetAllNotesUseCase)
//    singleOf(::DeleteNoteUseCase)
//    singleOf(::UpdateNoteUseCase)
//    singleOf(::GetNoteUseCase)
//}
//
//val provideViewModelModule = module {
//    viewModelOf(::CreateNoteViewModel)
//    viewModelOf(::HomeViewModel)
//}

val appModule = module {
    factory {}
    single {}
}