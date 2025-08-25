package com.came.parkare.dashboardapp.ui.screens.home.initmodal

import androidx.lifecycle.ViewModel
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.ui.components.dialog.AppDialogState
import com.came.parkare.dashboardapp.ui.screens.home.utils.ResourceUtils
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InitialModalViewModel(
    private val resourceUtils: ResourceUtils,
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {

    private val _state = MutableStateFlow(InitialModalState())
    val state: StateFlow<InitialModalState>
        get() = _state.asStateFlow()

    fun onCreateNewConfig(onRedirect: () -> Unit){
        val template = ConfigTemplateModel(
            templateName = "Blank template",
            screens = emptyList()
        )
        resourceUtils.setEditableTemplate(template)
        onRedirect.invoke()
    }

    fun onEditExistingConfig(){
        _state.update { it.copy(showTemplates = !it.showTemplates) }

    }
}