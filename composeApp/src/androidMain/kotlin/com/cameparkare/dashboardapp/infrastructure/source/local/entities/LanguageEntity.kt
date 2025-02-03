package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.LANGUAGE_TABLE_NAME

@Entity(tableName = LANGUAGE_TABLE_NAME)
data class LanguageEntity(
    @PrimaryKey(autoGenerate = true)
    override val localId: Long = 0,
    val screenId: String,
    val language: String,
    val linesData: List<String>
): BaseEntity()