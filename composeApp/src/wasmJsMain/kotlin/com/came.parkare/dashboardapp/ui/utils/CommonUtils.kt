package com.came.parkare.dashboardapp.ui.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class CommonUtils {
    inline fun<reified T> getPrettyJson(data: T): String {
        val prettyJson = Json {
            prettyPrint = true
            prettyPrintIndent = " "
            encodeDefaults = true
        }
        return prettyJson.encodeToString<T>(data)
    }
}