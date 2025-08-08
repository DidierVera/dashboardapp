package com.came.parkare.dashboardapp.ui.screens.home.initmodal

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler

class InitialModalViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {

    fun onCreateNewConfig(){

    }

    fun onEditExistingConfig(){

    }

    fun onNavigateToSettings(message: String){
        val dialogModel = AppDialogState(

        )
        wasmUtilsHandler.showDialogRequestPassword(dialogModel)
    }
}