package com.cameparkare.dashboardapp.ui.interfaces

import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import kotlinx.coroutines.flow.StateFlow

interface IServerConnection {
    val typeConnection: StateFlow<TypeConnectionEnum>
    val statusConnection: StateFlow<Boolean>

    fun setTypeConnection(type: TypeConnectionEnum)
    fun setStatusConnection(status: Boolean)
}