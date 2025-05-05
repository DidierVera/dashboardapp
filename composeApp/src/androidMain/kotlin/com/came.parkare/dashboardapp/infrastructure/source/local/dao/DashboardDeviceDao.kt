package com.came.parkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.DashboardDeviceEntity

@Dao
interface DashboardDeviceDao {
    @Insert
    suspend fun insert(entity: DashboardDeviceEntity)

    @Delete
    suspend fun delete(entity: DashboardDeviceEntity)

    @Query("SELECT * FROM tbl_dashboard_device")
    suspend fun getAll(): List<DashboardDeviceEntity>

    @Query("SELECT * FROM tbl_dashboard_device WHERE id = :id")
    suspend fun getById(id: Long): DashboardDeviceEntity?

    @Update
    suspend fun update(entity: DashboardDeviceEntity)
}