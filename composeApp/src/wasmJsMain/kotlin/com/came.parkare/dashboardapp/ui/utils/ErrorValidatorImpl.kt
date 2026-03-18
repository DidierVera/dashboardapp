package com.came.parkare.dashboardapp.ui.utils

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ErrorValidator

class ErrorValidatorImpl(
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val appLogger: AppLogger
): ErrorValidator {
    override fun validate(error: ErrorTypeClass) {
        when (error) {
            ErrorTypeClass.CanNoAccessToConfigFile -> appLogger.trackLog("CanNoAccessToConfigFile")
            ErrorTypeClass.ConfigFileNotExist -> appLogger.trackLog("ConfigFileNotExist")
            is ErrorTypeClass.GeneralException -> {
                val message = """
                ${error.messageError}\r\n
                ${error.additionalText}
            """.trimMargin()
                appLogger.trackLog("GeneralException", message)
                wasmUtilsHandler.showToastMessage(message)
            }
            ErrorTypeClass.NotSocketResponse -> appLogger.trackLog("NotSocketResponse")
            ErrorTypeClass.SocketConnectionError -> appLogger.trackLog("SocketConnectionError")
            ErrorTypeClass.WrongConfigFile -> appLogger.trackLog("WrongConfigFile")
            is ErrorTypeClass.ResourceGeneralException -> {
                appLogger.trackLog("ResourceGeneralException")
                wasmUtilsHandler.showToastMessage(error.resMessageId)
            }
        }
    }
}