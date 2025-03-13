package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity

@Dao
interface ScreenDao {
    @Insert
    suspend fun insertScreen(screen: ScreenEntity)
    @Insert
    suspend fun insertAll(screens: List<ScreenEntity>): LongArray

    @Query("SELECT * FROM tbl_screen")
    suspend fun getAll(): List<ScreenEntity>

    @Query("SELECT * FROM tbl_screen WHERE screenId = :screenId")
    suspend fun getScreenById(screenId: String): ScreenEntity?

    @Query("SELECT * FROM tbl_screen WHERE dispatcherCode = :dispatcher")
    suspend fun getScreenByDispatcher(dispatcher: Long): ScreenEntity?

    @Query("DELETE FROM tbl_screen WHERE screenId = :screenId")
    suspend fun deleteScreen(screenId: String)
}
