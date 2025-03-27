package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ImagesFilesEntity

@Dao
interface ImagesDao {
    @Insert
    suspend fun insertImage(image: ImagesFilesEntity)
    @Insert
    suspend fun insertAll(images: List<ImagesFilesEntity>): LongArray

    @Query("SELECT * FROM tbl_images")
    suspend fun getAll(): List<ImagesFilesEntity>

    @Query("DELETE FROM tbl_images WHERE id = :id")
    suspend fun deleteImage(id: Long)

    @Query("DELETE FROM tbl_images")
    suspend fun deleteAllImages()
}