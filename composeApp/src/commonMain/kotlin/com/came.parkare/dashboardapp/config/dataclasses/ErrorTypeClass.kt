package com.came.parkare.dashboardapp.config.dataclasses

import org.jetbrains.compose.resources.StringResource

sealed class ErrorTypeClass {
    data class GeneralException(
        val messageError: String? = null,
        val additionalText: String? = null): ErrorTypeClass()
    data class ResourceGeneralException(
        val resMessageId: StringResource
    ) : ErrorTypeClass()
    data object ConfigFileNotExist : ErrorTypeClass()
    data object WrongConfigFile: ErrorTypeClass()
    data object CanNoAccessToConfigFile: ErrorTypeClass()
    data object NotSocketResponse: ErrorTypeClass()
    data object SocketConnectionError: ErrorTypeClass()
}