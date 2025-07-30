package com.came.parkare.dashboardapp.ui.screens.home.configeditor

sealed class ConfigEditorUiState {
    object Loading : ConfigEditorUiState()
    object Success : ConfigEditorUiState()
    data class Error(val message: String) : ConfigEditorUiState()
}

