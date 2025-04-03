package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ImagesFilesEntity

@Dao
interface ImagesDao {
    @Insert
    suspend fun insertImage(image: ImagesFilesEntity)
    @Insert
    suspend fun insertAll(images: List<ImagesFilesEntity>): LongArray

    @Query("SELECT * FROM tbl_images")
    suspend fun getAll(): List<ImagesFilesEntity>

    @Query("SELECT * FROM tbl_images WHERE fileName =:fileName")
    suspend fun getByName(fileName: String): ImagesFilesEntity?

    @Query("DELETE FROM tbl_images WHERE id = :id")
    suspend fun deleteImage(id: Long)

    @Query("DELETE FROM tbl_images")
    suspend fun deleteAllImages()

    @Query("DELETE FROM tbl_images WHERE id IN (:ids)")
    suspend fun deleteImagesByIds(ids: List<Long>): Int

    @Update
    suspend fun updateImage(image: ImagesFilesEntity): Int

    @Update
    suspend fun updateImages(images: List<ImagesFilesEntity>): Int

    @Query("SELECT * FROM tbl_images WHERE id = :id")
    suspend fun getById(id: Long): ImagesFilesEntity?

    @Transaction
    suspend fun replaceAllImages(newImages: List<ImagesFilesEntity>) {
        deleteAllImages() // Step 1: Clear old data
        insertAll(newImages) // Step 2: Insert new data (atomic operation)
    }
}