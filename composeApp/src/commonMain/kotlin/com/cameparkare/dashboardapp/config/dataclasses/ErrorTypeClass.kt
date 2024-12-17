package com.cameparkare.dashboardapp.config.dataclasses

sealed class ErrorTypeClass {
    data class GeneralException(
        val messageError: String? = null,
        val additionalText: String? = null): ErrorTypeClass()
    object ConfigFileNotExist : ErrorTypeClass()
    object WrongConfigFile: ErrorTypeClass()
    object CanNoAccessToConfigFile: ErrorTypeClass()
    object NotSocketResponse: ErrorTypeClass()
    object SocketConnectionError: ErrorTypeClass()
}