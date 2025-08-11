package com.came.parkare.dashboardapp.ui.screens.home.templates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetTemplates
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TemplateViewModel(
    private val getTemplates: GetTemplates,
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler
): ViewModel() {
    private val _state = MutableStateFlow(TemplateState())
    val state: StateFlow<TemplateState>
        get() = _state.asStateFlow()

    fun initScreen(){
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            when(val result = getTemplates.invoke()){
                is ServiceResult.Error -> {
                    validator.validate(result.error)
                    wasmUtilsHandler.showLoading(false)
                }
                is ServiceResult.Success -> {
                    _state.update { it.copy(items = result.data.orEmpty()) }
                    wasmUtilsHandler.showLoading(false)
                }
            }
        }
    }
}