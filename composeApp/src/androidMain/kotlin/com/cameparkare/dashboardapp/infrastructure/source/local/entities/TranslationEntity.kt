package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.TRANSLATION_TABLE_NAME

@Entity(
    tableName = TRANSLATION_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ElementEntity::class,
            parentColumns = ["id"],
            childColumns = ["element_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TranslationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "element_id")
    val elementId: Int,
    @ColumnInfo(name = "language_code")
    val languageCode: String,
    @ColumnInfo(name = "text")
    val text: String
)
