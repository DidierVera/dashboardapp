package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.TranslationEntity

@Dao
interface ElementDao {
    @Insert
    suspend fun insertElement(element: ElementEntity)

    @Insert
    suspend fun insertTranslation(translation: TranslationEntity)

    @Query("SELECT * FROM tbl_elements WHERE screen_id = :screenId")
    suspend fun getElements(screenId: Int): List<ElementEntity>
}
