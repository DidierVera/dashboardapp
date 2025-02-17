package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME

@Entity(tableName = SCREEN_TABLE_NAME)
data class ScreenEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    val dispatchCode: Int,
    val screenId: String
)
