package com.cameparkare.dashboardapp.infrastructure.source.local.entities.config

import androidx.room.TypeConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
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