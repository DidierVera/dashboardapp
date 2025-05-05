package com.came.parkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ConfigTemplateEntity

@Dao
interface ConfigTemplateDao {
    @Insert
    suspend fun insertTemplate(template: ConfigTemplateEntity): Long

    @Query("SELECT * FROM tbl_configTemplate")
    suspend fun getAllTemplates(): List<ConfigTemplateEntity>

    @Update
    suspend fun updateTemplate(newValue: ConfigTemplateEntity)

    @Delete
    suspend fun deleteTemplate(template: ConfigTemplateEntity)
}