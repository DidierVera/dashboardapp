package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.domain.models.ScreenModel
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