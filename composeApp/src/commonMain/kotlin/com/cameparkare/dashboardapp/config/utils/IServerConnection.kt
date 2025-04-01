package com.cameparkare.dashboardapp.config.utils

import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import kotlinx.coroutines.flow.StateFlow

interface IServerConnection {
    val typeConnection: StateFlow<TypeConnectionEnum>
    val statusConnection: StateFlow<Boolean>
    val restartApp: StateFlow<Boolean>
    val screensList: StateFlow<List<ScreenModel>>

    fun setRestartApp(restart: Boolean)
    fun setTypeConnection(type: TypeConnectionEnum)
    fun setStatusConnection(status: Boolean)
    fun setScreensList(screens: List<ScreenModel>)
}