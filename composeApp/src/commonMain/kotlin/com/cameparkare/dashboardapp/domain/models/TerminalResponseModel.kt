package com.cameparkare.dashboardapp.domain.models



data class TerminalResponseModel(
    val dispatcherCode: Int,
    val ditsTUI: Map<String, String>?
)
