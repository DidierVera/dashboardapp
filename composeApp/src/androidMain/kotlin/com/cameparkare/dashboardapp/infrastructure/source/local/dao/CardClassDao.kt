package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomDatabase
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.CardClassEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames

@Dao
abstract class CardClassDao(roomDatabase: RoomDatabase): BaseDao<CardClassEntity>(RoomTableNames.CARD_CLASS_TABLE_NAME, roomDatabase) {
    @Query("SELECT * FROM tbl_cardClass WHERE elementCode = :code")
    abstract suspend fun selectByElementCode(code: Int): List<CardClassEntity>?
}