package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ServerConnectionImpl(): IServerConnection {

    private val _statusConnection: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _typeConnection = MutableStateFlow(TypeConnectionEnum.SIGNAL_R)
    override val typeConnection: StateFlow<TypeConnectionEnum>
        get() = _typeConnection.asStateFlow()
    override val statusConnection: StateFlow<Boolean>
        get() = _statusConnection.asStateFlow()

    override fun setTypeConnection(type: TypeConnectionEnum) {
        _typeConnection.update { type }
    }

    override fun setStatusConnection(status: Boolean) {
        _statusConnection.update { status }
    }
}