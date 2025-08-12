package com.came.parkare.dashboardapp.ui.screens.home.initmodal

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InitialModalViewModel(
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {

    private val _state = MutableStateFlow(InitialModalState())
    val state: StateFlow<InitialModalState>
        get() = _state.asStateFlow()

    fun onCreateNewConfig(){

    }

    fun onEditExistingConfig(){
        _state.update { it.copy(showTemplates = !it.showTemplates) }

    }

    fun onNavigateToSettings(message: String){
        val dialogModel = AppDialogState(

        )
        wasmUtilsHandler.showDialogRequestPassword(dialogModel)
    }
}