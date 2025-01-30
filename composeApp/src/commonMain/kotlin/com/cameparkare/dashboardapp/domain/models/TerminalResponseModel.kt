package com.cameparkare.dashboardapp.domain.models

import kotlinx.serialization.json.JsonArray

data class TerminalResponseModel(
    val dispatcherCode: Int,
    val ditsTUI: JsonArray?
)
