package com.came.parkare.dashboardapp.ui.screens.settings.testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.ErrorValidator
import com.came.parkare.dashboardapp.domain.usecases.GetScreensConfig
import com.came.parkare.dashboardapp.domain.usecases.SendDitTesting
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.ScreenDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.testing.DitTestingDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.testing.SendDitTestingDto
import com.came.parkare.dashboardapp.ui.utils.WasmUtilsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.orEmpty

class TestingViewModel(
    private val validator: ErrorValidator,
    private val wasmUtilsHandler: WasmUtilsHandler,
    private val getScreensConfig: GetScreensConfig,
    private val sendDitTesting: SendDitTesting,
) : ViewModel() {

    private val _state = MutableStateFlow(TestingState())
    val state: StateFlow<TestingState>
        get() = _state.asStateFlow()

    fun initConfig() {
        viewModelScope.launch {
            getCurrentScreenConfig()
        }
    }

    fun selectScreen(screen: ScreenDto) {
        val groups = extractDitGroups(screen)
        _state.update { it.copy(selectedScreen = screen, ditFormGroups = groups) }
    }

    fun updateField(ditTypeCode: Int, key: String, value: String) {
        _state.update { state ->
            val updatedGroups = state.ditFormGroups.map { group ->
                if (group.ditTypeCode == ditTypeCode) {
                    group.copy(
                        fields = group.fields.map { field ->
                            if (field.key == key) field.copy(value = value) else field
                        }
                    )
                } else group
            }
            state.copy(ditFormGroups = updatedGroups)
        }
    }

    fun sendDitConfig() {
        val state = _state.value
        val screen = state.selectedScreen ?: return
        val groups = state.ditFormGroups

        _state.update { it.copy(isSending = true) }
        viewModelScope.launch {
            wasmUtilsHandler.showLoading(true)
            val dto = SendDitTestingDto(
                dispatchCode = screen.dispatchCode,
                screenId = screen.screenId,
                dits = groups.map { group ->
                    DitTestingDto(
                        ditTypeCode = group.ditTypeCode,
                        ditName = group.ditName,
                        fields = group.fields.associate { it.key to it.value },
                    )
                },
            )
            when (val result = sendDitTesting.invoke(dto)) {
                is ServiceResult.Error -> {
                    validator.validate(error = result.error)
                }
                is ServiceResult.Success -> {
                    wasmUtilsHandler.showToastMessage("Dit config sent successfully")
                }
            }
            wasmUtilsHandler.showLoading(false)
            _state.update { it.copy(isSending = false) }
        }
    }

    private fun extractDitGroups(screen: ScreenDto): List<DitFormGroup> {
        val ditCodes = mutableSetOf<Int>()
        collectDitCodes(screen.data, ditCodes)
        return ditCodes.mapNotNull(::createDitFormGroup)
    }

    private fun collectDitCodes(elements: List<ElementDto>, codes: MutableSet<Int>) {
        for (element in elements) {
            when (element) {
                is ElementDto.BoxDto -> {
                    element.data.ditTypeCode?.let { codes.add(it) }
                    collectDitCodes(element.data.content, codes)
                }
                is ElementDto.ColumnDto -> {
                    element.data.ditTypeCode?.let { codes.add(it) }
                    collectDitCodes(element.data.content, codes)
                }
                is ElementDto.RowDto -> {
                    element.data.ditTypeCode?.let { codes.add(it) }
                    collectDitCodes(element.data.content, codes)
                }
                is ElementDto.TextDto -> {
                    element.data.ditTypeCode?.let { codes.add(it) }
                }
                is ElementDto.ImageDto -> {
                    element.data.ditTypeCode?.let { codes.add(it) }
                }
                is ElementDto.VideoDto -> {
                    element.data.ditTypeCode?.let { codes.add(it) }
                }
                is ElementDto.SpacerDto -> { }
            }
        }
    }

    private fun createDitFormGroup(ditTypeCode: Int): DitFormGroup? {
        val info = knownDitFields[ditTypeCode] ?: return null
        return DitFormGroup(
            ditTypeCode = ditTypeCode,
            ditName = info.first,
            fields = info.second.map { DitFormField(key = it, value = "") },
        )
    }

    companion object {
        private val knownDitFields: Map<Int, Pair<String, List<String>>> = mapOf(
            3 to ("Amount To Pay" to listOf("AmountTotal", "AmountAlreadyPayed")),
            7 to ("Current Card Reader" to listOf("CardReader")),
            9 to ("Current Card Type" to listOf("CardClass", "CardType")),
            10 to ("Current License Plates" to listOf("MainLicensePlate")),
            18 to ("Issuer Status" to listOf("Status")),
            19 to ("Reader Status" to listOf("Status")),
        )
    }

    private suspend fun getCurrentScreenConfig() {
        wasmUtilsHandler.showLoading(true)
        when (val result = getScreensConfig.invoke()) {
            is ServiceResult.Error -> {
                validator.validate(error = result.error)
                wasmUtilsHandler.showLoading(false)
            }
            is ServiceResult.Success -> {
                wasmUtilsHandler.showLoading(false)
                _state.update { it.copy(screens = result.data.orEmpty()) }
            }
        }
    }
}