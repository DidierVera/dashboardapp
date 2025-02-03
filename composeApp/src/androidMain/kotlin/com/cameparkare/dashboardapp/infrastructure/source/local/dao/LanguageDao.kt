package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomDatabase
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME

@Dao
abstract class ScreenDao(roomDatabase: RoomDatabase): BaseDao<ScreenEntity>(SCREEN_TABLE_NAME, roomDatabase){
    @Query("SELECT * FROM tbl_screen WHERE dispatchCode = :dispatcher LIMIT 1")
    abstract suspend fun getByDispatcher(dispatcher: Int): ScreenEntity?
}