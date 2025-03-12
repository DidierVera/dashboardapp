package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity

@Dao
interface ElementDao {
    @Insert
    suspend fun insertElement(element: ElementEntity)

    @Query("SELECT * FROM tbl_elements WHERE id = :id")
    suspend fun getElementById(id: Long): ElementEntity?

    @Query("DELETE FROM tbl_elements WHERE id = :id")
    suspend fun deleteElement(id: Long)
}
