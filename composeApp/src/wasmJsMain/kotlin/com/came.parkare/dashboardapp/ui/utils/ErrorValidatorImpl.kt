package com.came.parkare.dashboardapp.ui.utils

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.utils.ErrorValidator

class ErrorValidatorImpl(
    private val wasmUtilsHandler: WasmUtilsHandler
): ErrorValidator {
    override fun validate(error: ErrorTypeClass) {
        when (error) {
            ErrorTypeClass.CanNoAccessToConfigFile -> TODO()
            ErrorTypeClass.ConfigFileNotExist -> TODO()
            is ErrorTypeClass.GeneralException -> {
                val message = """
                ${error.messageError}\r\n
                ${error.additionalText}
            """.trimMargin()
                wasmUtilsHandler.showToastMessage(message)
            }
            ErrorTypeClass.NotSocketResponse -> TODO()
            ErrorTypeClass.SocketConnectionError -> TODO()
            ErrorTypeClass.WrongConfigFile -> TODO()
            is ErrorTypeClass.ResourceGeneralException -> {
                wasmUtilsHandler.showToastMessage(error.resMessageId)
            }
        }
    }
}