package com.came.parkare.dashboardapp.config.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.DashboardDeviceDao
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.ImagesDao
import com.came.parkare.dashboardapp.infrastructure.source.local.dao.ScreenDao
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.DashboardDeviceEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ImagesFilesEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.MapConverter


// Room database
@Database(
    entities = [
        ScreenEntity::class,
        DashboardDeviceEntity::class,
        ImagesFilesEntity::class
    ],
    version = 4
)
@TypeConverters(ElementListConverter::class, MapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun screenDao(): ScreenDao
    abstract fun imagesDao(): ImagesDao
    abstract fun dashboardDeviceDao(): DashboardDeviceDao
}