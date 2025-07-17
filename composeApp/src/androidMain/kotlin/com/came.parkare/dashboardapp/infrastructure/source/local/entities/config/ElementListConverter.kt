package com.came.parkare.dashboardapp.infrastructure.source.local.entities.config

import androidx.room.TypeConverter
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity
import com.google.common.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

class ElementListConverter {
    val json = Json
    @TypeConverter
    fun fromElementList(value: List<ElementEntity>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toElementList(value: String): List<ElementEntity> {
        return json.decodeFromString<List<ElementEntity>>(value)
    }
}
class ScreenListConverter {
    val json = Json
    @TypeConverter
    fun fromScreenList(value: List<ScreenEntity>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toScreenList(value: String): List<ScreenEntity> {
        return json.decodeFromString<List<ScreenEntity>>(value)
    }
}

class MapConverter {
    val json = Json
    @TypeConverter
    fun fromMap(value: Map<String, String>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toMap(value: String): Map<String, String> {
        return  json.decodeFromString<Map<String, String>>(value)
    }
}